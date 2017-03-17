public class Testing {
	public static void main (String [] args) {
		//............................................................................
		int dim = 5;
		char ch = 'A';
		String[][] array;
		array = new String[dim][];
		for( int i = 0 ; i < dim ; i++ ) {
			array[i] = new String[dim];
			for( int j = 0 ; j < dim ; j++, ch++ ) {
				array[i][j] = "" + ch;
			}
		}
		//..............................................................................
		 for( int i = 0 ; i < dim ; i++ ) {
			for( int j = 0 ; j < dim ; j++, ch++ ) {
				System.out.print( array[i][j] + " " );
			}
			System.out.println();
		}
		System.out.println( "============================" );
		//...............................................................................
		 for( int j = dim-1 ; j >=0 ; j-- ) {
			for( int k = 0 ; k <= j ; k++ ) {
				
				if( k < dim && j < dim ) {
				System.out.print( array[k][j] + " " );
				}
			}
			System.out.println();
		}
		
	}
    
}
