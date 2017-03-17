
/**
* AudioManipulation.java
*
* 
* To compile: javac -classpath editor.jar:. RunEffects.java
* To run use: java -classpath editor.jar:. RunEffects
* 
*/

import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class AudioManipulation {

	/**** echo *****************************************************************/

	public static AudioInputStream echo(AudioInputStream ais, int timeDelay, double fading0, double fading1) {

		byte[] a = null;
		int[] data, ch0, ch1;
		int max;

		try {

			// AudioInputStream methods
			int numChannels = ais.getFormat().getChannels();
			int sampleSize = ais.getFormat().getSampleSizeInBits();
			boolean isBigEndian = ais.getFormat().isBigEndian();
			float sampleRate = ais.getFormat().getSampleRate();
			float frameRate = ais.getFormat().getFrameRate();
			int frameSize = ais.getFormat().getFrameSize();
			int frameLength = (int) ais.getFrameLength();

			// sampleRate = framerate = 44100.0 Hz (playback rate = sampling
			// rate!)
			// 1 sec = 1000 millisecs
			// calculate delay in frames
			int frameDelay = (int) (timeDelay / 1000.0 * frameRate);

			// reset the AudioInputStream (mark goes to the start)
			ais.reset();

			// create a byte array of the right size
			// recall the lecture OHP slides ..
			a = new byte[(int) frameLength * frameSize];

			// fill the byte array with the data of the AudioInputStream
			ais.read(a);

			// Create an integer array, data, of the right size
			// only reason to do this is enabling type double mixing
			// calculations
			data = new int[a.length / 2];

			// fill the integer array by combining two bytes of the
			// byte array a into one integer
			// Bytes HB and LB Big Endian make up one integer
			for (int i = 0; i < data.length; ++i) {
				/*
				 * First byte is HB (most significant digits) - coerce to 32-bit
				 * int
				 */
				// HB =def sign_extend(a[2*i]) from 8 bit byte to 32 bit int
				int HB = (int) a[2 * i];
				/*
				 * Second byte is LB (least significant digits) - coerce to
				 * 32-bit int
				 */
				// LB =def sign_extend(a[2*i+1]) from 8 bit byte to 32 bit int
				int LB = (int) a[2 * i + 1];
				// note that data[i] =def sign_extend(HB.LB)
				// | : Bool^32 x Bool^32 -----> Bool^32 where Bool = {0, 1}
				data[i] = HB << 8 | (LB & 0xff);
			}

			// split samples into two channels
			// if both channels are faded by the same factor
			// then there is no need to split the channels
			ch0 = new int[data.length / 2];
			ch1 = new int[data.length / 2];
			for (int i = 0; i < data.length / 2; i++) {
				ch0[i] = data[2 * i];
				ch1[i] = data[2 * i + 1];
			}

			// Adding a faded copy of the early signal to the later signal
			// THIS IS THE ECHO !!
			for (int i = frameDelay; i < ch0.length; ++i) {
				ch0[i] += (int) (ch0[i - frameDelay] * fading0);
				ch1[i] += (int) (ch1[i - frameDelay] * fading1);
			}

			// combine the two channels
			for (int i = 0; i < data.length; i += 2) {
				data[i] = ch0[i / 2];
				data[i + 1] = ch1[i / 2];
			}

			// get the maximum amplitute
			max = 0;
			for (int i = 0; i < data.length; ++i) {
				max = Math.max(max, Math.abs(data[i]));
			}

			// 16 digit 2s-complement range from -2^15 to +2^15-1 = 256*128-1
			// therefore we linearly scale data[i] values to lie within this
			// range ..
			// .. so that each data[i] has a 16 digit "HB.LB binary
			// representation"
			if (max > 256 * 128 - 1) {
				System.out.println("Sound values are linearly scaled by " + (256.0 * 128.0 - 1) / max
						+ " because maximum amplitude is larger than upper boundary of allowed value range.");
				for (int i = 0; i < data.length; ++i) {
					data[i] = (int) (data[i] * (256.0 * 128.0 - 1) / max);
				}
			}

			// convert the integer array to a byte array
			for (int i = 0; i < data.length; ++i) {
				a[2 * i] = (byte) ((data[i] >> 8) & 0xff);
				a[2 * i + 1] = (byte) (data[i] & 0xff);
			}

		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		// create a new AudioInputStream out of the the byteArray
		// and return it.
		return new AudioInputStream(new ByteArrayInputStream(a), ais.getFormat(), ais.getFrameLength());
	}

	/**** scaleToZero *****************************************************************/

	public static AudioInputStream scaleToZero(AudioInputStream ais) {

		byte[] a = null;
		int[] data, ch0, ch1;
		int max;

		try {
			int frameSize = ais.getFormat().getFrameSize();
			int frameLength = (int) ais.getFrameLength();
			// reset the AudioInputStream (mark goes to the start)
			ais.reset();
			// create a byte array of the right size
			a = new byte[(int) frameLength * frameSize];
			// fill the byte array with the data of the AudioInputStream
			ais.read(a);
			// Create an integer array, data, of the right size // only reason
			// to do this is enabling type float/double mixing calculations
			data = new int[a.length / 2];
			// fill the integer array by combining two bytes of the
			// byte array a into one integer
			// Bytes HB and LB Big Endian make up one integer
			for (int i = 0; i < data.length; ++i) {
				/*
				 * First byte is HB (most significant digits) - coerce to 32-bit
				 * int
				 */
				// HB =def sign_extend(a[2*i]) from 8 bit byte to 32 bit int
				int HB = (int) a[2 * i];
				/*
				 * Second byte is LB (least significant digits) - coerce to
				 * 32-bit int
				 */
				// LB =def sign_extend(a[2*i+1]) from 8 bit byte to 32 bit int
				int LB = (int) a[2 * i + 1];
				// note that data[i] =def sign_extend(HB.LB)
				// | : Bool^32 x Bool^32 -----> Bool^32 where Bool = {0, 1}
				data[i] = HB << 8 | (LB & 0xff);
			}
			// scale data linearly to zero // **** NB this is the only part of
			// scaleToZero that is not already part of // echo effect !!!! ****
			// --------------NEEDS FILLING--------------/

			for (int i = 0; i < data.length - 1; i++) {
				// work out the scale factor
				double scale = 1 - (double) i / data.length;
				// scale the data in each channel by the scale factor
				data[i] = (int) (data[i] * scale);
			}
			// get the maximum amplitute
			max = 0;
			for (int i = 0; i < data.length; ++i) {
				max = Math.max(max, Math.abs(data[i]));
			}
			// 16 digit 2s-complement range from -2^15 to +2^15-1 = 256*128-1
			// therefore we linearly scale data[i] values to lie within this
			// range ..
			// .. so that each data[i] has a 16 digit "HB.LB binary
			// representation"
			if (max > 256 * 128 - 1) {
				System.out.println("Sound values are linearly scaled by " + (256.0 * 128.0 - 1) / max
						+ " because maximum amplitude is larger than upper boundary of allowed value range.");
				for (int i = 0; i < data.length; ++i) {
					data[i] = (int) (data[i] * (256.0 * 128.0 - 1) / max);
				}
			}

			// convert the integer array to a byte array
			for (int i = 0; i < data.length; ++i) {
				a[2 * i] = (byte) ((data[i] >> 8) & 0xff);
				a[2 * i + 1] = (byte) (data[i] & 0xff);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		// create a new AudioInputStream out of the the byteArray
		// and return it.
		return new AudioInputStream(new ByteArrayInputStream(a), ais.getFormat(), ais.getFrameLength());
	}

	/**** addNote *****************************************************************/

	public static AudioInputStream addNote(AudioInputStream ais, double frequency, int noteLengthInMilliseconds) {
		byte[] a = null;
		int[] data;
		int frameSize = ais.getFormat().getFrameSize();
		int numChannels = ais.getFormat().getChannels();
		try {
			// number of frames for the note of noteLengthInMilliseconds
			float frameRate = ais.getFormat().getFrameRate();
			float noteLengthInFrames = ((float) (noteLengthInMilliseconds / 1000.0) * frameRate);
			int noteLengthInBytes = (int) noteLengthInFrames * frameSize;
			int noteLengthInInts = (int) noteLengthInFrames * numChannels;

			a = new byte[noteLengthInBytes];
			data = new int[noteLengthInInts];
			//calculate amplitude
			double amplitude = 64.0 * 256.0;
			// create the note as a data array of samples 
			// each sample value data[i] is calculated using 
			// the time t at which data[i] is played
			for (int i = 0; i < noteLengthInInts; i += 2) {
				double timeToPlayOneFrame = (double) (1 / frameRate);
				double t = (i / numChannels) * (timeToPlayOneFrame);
				data[i] = (int) (amplitude * Math.sin(frequency * 2 * (Math.PI) * t));
				data[i + 1] = (int) (amplitude * Math.sin(frequency * 2 * (Math.PI) * t));
			}
			// copy the int data[i] array into byte a[i] array 	
			for (int i = 0; i < data.length; ++i) {
				a[2 * i] = (byte) ((data[i] >> 8) & 0xff);
				a[2 * i + 1] = (byte) (data[i] & 0xff);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return append(new AudioInputStream(new ByteArrayInputStream(a), ais.getFormat(),
				a.length / ais.getFormat().getFrameSize()), ais);

	} // end addNote

	/**** append *****************************************************************/

	// THIS METHOD append IS SUPPLIED FOR YOU
	public static AudioInputStream append(AudioInputStream ais1, AudioInputStream ais2) {

		byte[] a, b, c = null;
		try {
			a = new byte[(int) ais1.getFrameLength() * ais1.getFormat().getFrameSize()];

			// fill the byte array with the data of the AudioInputStream
			ais1.read(a);
			b = new byte[(int) ais2.getFrameLength() * ais2.getFormat().getFrameSize()];

			// fill the byte array with the data of the AudioInputStream
			ais2.read(b);

			c = new byte[a.length + b.length];
			for (int i = 0; i < c.length; i++) {
				if (i < a.length) {
					c[i] = a[i];
				} else
					c[i] = b[i - a.length];
			}

		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return new AudioInputStream(new ByteArrayInputStream(c), ais1.getFormat(),
				c.length / ais1.getFormat().getFrameSize());
	} // end append

	/**** tune *****************************************************************/

	public static AudioInputStream tune(AudioInputStream ais) {
		
		// create an empty AudioInputStream (of frame size 0) // call it temp
		AudioInputStream temp = null;
		byte[] c = new byte[1];
		temp = new AudioInputStream(new ByteArrayInputStream(c), ais.getFormat(), 0);
		/* specify variable names for both the frequencies in Hz and note
		 * lengths in seconds // eg double C4, D4 etc for frequencies and s, l,
		 * ll, lll for lengths // Hint: Each octave results in a doubling of
		 * frequency.
		 */
		double C4 = 261.63;
		double D4 = 293.66;
		double Eb4 = 311.13;
		double E4 = 329.63;
		double F4 = 349.23;
		double G4 = 392.00;
		double A4 = 440.00;
		double B4 = 493.88;
		double C5 = 523.26;
		double D5 = 587.32;
		double Eb5 = 622.26;
		double E5 = 659.26;
		double F5 = 698.46;
		double G5 = 784.00;
		double A5 = 880.00;
		double B5 = 987.76;
		double C6 = 1046.52;
		double D6 = 1174.64;
		double Eb6 = 1244.52;
		double E6 = 1318.52;
		double F6 = 1396.92;
		double G6 = 1568.00;
		double A6 = 1760.00;
		double B6 = 1975.52;
		double C7 = 2093.04;

		// and the lengths in milliseconds int s = 500 etc
		int s = 500;
		int l = 2000;
		int ll = 2500;
		int lll = 2800;

		// C4l G4l C5l
		// E5s Eb5lll
		// C4l G4l C5l
		// Eb5s E5lll
		// A5s B5s C6l
		// A5s B5s C6l
		// D6ll
		// E6s F6s G6l
		// E6s F6s G6l
		// A6l B6l C7lll
		
		// also sprach zarathustra: 2001 A Space Odyssey // specify the tune
		//double [][] notes = { {C4,l}, etc etc ?? };
		double[][] notes = { { C4, l }, { G4, l }, { C5, l }, { E5, s }, { Eb5, lll }, { C4, l }, { G4, l }, { C5, l },
				{ Eb5, s }, { E5, lll }, { A5, s }, { B5, s }, { C6, l }, { A5, s }, { B5, s }, { C6, l }, { D6, ll },
				{ E6, s }, { F6, s }, { G6, l }, { E6, s }, { F6, s }, { G6, l }, { A6, l }, { B6, l }, { C7, lll } };

		 //use addNote to build the tune as an AudioInputStream // starting
		 //from the empty AudioInputStream temp (above) and adding each note one
		 //by one USE A LOOP !!!! ??
		for (int i = notes.length - 1; i >= 0; i--) {
			double frequency = notes[i][0];
			int noteLength = (int) notes[i][1];
			if (i == 9 || i == 4) {
				temp = addNote(temp, 0, 500);
				temp = addNote(temp, notes[i][0], (int) notes[i][1]);
			} else {
				temp = addNote(temp, 0, 100);
				temp = addNote(temp, notes[i][0], (int) notes[i][1]);
			}
		}
		//append the temp with ais.
		return append(temp, ais);
	}

	/**** altChannels *****************************************************************/

	public static AudioInputStream altChannels(AudioInputStream ais, double timeInterval) {

		/*
		 * ----- template code commented out BEGIN
		 * 
		 * int frameSize = ais.getFormat().getFrameSize(); // = 4 float
		 * frameRate = ?? int frameInterval = ?? int inputLengthInBytes = ?? int
		 * numChannels = ais.getFormat().getChannels(); // = 2
		 * 
		 * ----- template code commented out END
		 */

		// byte arrays for input channels and output channels
		byte[] ich0, ich1, och0, och1;
		byte[] a = null, b = null;

		int frameSize = ais.getFormat().getFrameSize();
		float frameRate = ais.getFormat().getFrameRate();
		int frameInterval = (int) (timeInterval * frameRate);
		int inputLengthInBytes = (int) (ais.getFrameLength() * frameSize);

		int numChannels = ais.getFormat().getChannels();

		try {

			/*
			 * ----- template code commented out BEGIN
			 * 
			 * // create new byte arrays a for input and b for output of the
			 * right size // ?? // fill the byte array a with the data of the
			 * AudioInputStream // ?? // create new byte arrays for input and
			 * output channels of the right size // eg ich0 = new
			 * byte[a.length/2]; // ?? // fill up ich0 and ich1 by splitting a
			 * ?? // compute the output channels from the input channels - this
			 * is the hard part.
			 * 
			 * 
			 * //use an index i to mark out start positions of double segments
			 * A0, B0, C0 etc of length 2*N //use index j to count the
			 * individual bytes in segments, going from 0 to N-1 //use index k
			 * to count the individual bytes in the final segment, whose length
			 * in bytes must be rem/2 = ?? //where rem is the remainder of outL
			 * when dividing outL by whole double segments of 2*N bytes
			 * 
			 * // MAIN CODE HERE
			 * 
			 * // join och0 and och1 into b for (int i=0; i < b.length; i += 4)
			 * { b[i] = och0[i/2]; // etc etc }
			 * 
			 * ----- template code commented out END
			 */
			// create new byte arrays a for input and b for output of the right size
			a = new byte[inputLengthInBytes];
			b = new byte[2 * inputLengthInBytes];
			// fill the byte array a with the data of the AudioInputStream
			ais.read(a);
			// create new byte arrays for input and output channels of the right size
			ich0 = new byte[a.length / 2];
			ich1 = new byte[a.length / 2];
			och0 = new byte[ich0.length * 2];
			och1 = new byte[ich1.length * 2];
			// compute the output channels from the input channels this is the hard part.
			for (int i = 0; i < a.length / 2; i += 2) { 
				ich0[i] = a[2 * i];
				ich0[i + 1] = a[2 * i + 1];
				ich1[i] = a[2 * i + 2];
				ich1[i + 1] = a[2 * i + 3];
			}

			/*
			 * //use an index i to mark out start positions of double segments
			 * A0, B0, C0 etc of length 2*N //use index j to count the
			 * individual bytes in segments, going from 0 to N-1 //use index k
			 * to count the individual bytes in the final segment, whose length
			 * in bytes must be rem/2 = ?? //where rem is the remainder of outL
			 * when dividing outL by whole double segments of 2*N bytes
			*/
			double N = (frameSize * frameInterval) / 2;
			int remainder = (int) (a.length % (2 * N));

			for (int i = 0; i < och0.length; i = (int) (i + (2 * N))) {
				if (i < och0.length - (2 * N)) {
					for (int j = 0; j < N; j++) {
						och0[i + j] = 0;
						och0[i + j + (int) N] = ich0[i / 2 + j];
						och1[i + j] = ich1[i / 2 + j];
						och1[i + j + (int) N] = 0;
					}
				}
			}
			
			// join och0 and och1 into b for (int i=0; i < b.length; i += 4) { b[i] = och0[i/2]; // etc etc }
			for(int i=0 ; i < b.length; i+=4) {
				b[i] = och0[i/2];
				b[i+1] = och0[i/2+1];
				b[i+2] = och1[i/2];
				b[i+3] = och1[i/2+1];
			}

		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		// return b
		return new AudioInputStream(new ByteArrayInputStream(b), ais.getFormat(),
				b.length / ais.getFormat().getFrameSize());

	} // end altChannels

} // AudioManipulation
