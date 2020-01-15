package com.ehsunhanif.eventsavvy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;

public class PaymentTestActivity extends AppCompatActivity {



    Token validatoryToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        //https://github.com/stripe/stripe-android
        CardMultilineWidget mCardInputWidget = (CardMultilineWidget) findViewById(R.id.card_input_widget);

        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            Toast.makeText(this, "Invalid Card Data", Toast.LENGTH_SHORT).show();
        } else {

            //final Card card = new Card("4242424242424242", 12, 2021, "123"); //Default Configuration
            String cardNumber = mCardInputWidget.getCard().getNumber();
            int expiryMonth = mCardInputWidget.getCard().getExpMonth();
            int expiryYear = mCardInputWidget.getCard().getExpYear();
            String cvc = mCardInputWidget.getCard().getCVC();
            Card card = new Card(cardNumber, expiryMonth, expiryYear, cvc);

            // Token Creation to use for taking payments
            if(!card.validateCard()) {
            } else {
                final Stripe stripe = new Stripe(this, "pk_test_SxrPTK7QrETZpYxeaHlRLy73");
                stripe.createToken(card, new TokenCallback() {
                    @Override
                    public void onError(Exception error) {

                    }

                    @Override
                    public void onSuccess(Token token) {
                        validatoryToken = token;
                        Log.d("PaymentTestActivity", "onSuccess: Token Received"+token);
                        Toast.makeText(PaymentTestActivity.this, "Token Received", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
