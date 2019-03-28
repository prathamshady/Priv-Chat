package com.example.abc.privchat;

import android.app.KeyguardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> mMessageList;
    private FirebaseAuth mAuth;

    private int[] key = new int[10000];
    private String key_str = "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";
    private int key_len = key_str.length();
    private String decrypted = "";
    private void Decryption(String str)
    {
        for(int i = 0; i < key_len; i++)
        {
            int ascii = (int)key_str.charAt(i);
            key[i] = ascii;
        }
        int nstr = str.length();
        int[] temp = new int[nstr];
        for(int i = 0; i < nstr; i++)
        {
            int ascii = (int)str.charAt(i);
            temp[i] = (ascii - key[i]);
            if(temp[i] < 0)
                temp[i] = temp[i] + 256;
            decrypted = decrypted + Character.toString((char)temp[i]).toString();
        }
    }

    public MessageAdapter(List<Messages> mMessageList) {
        this.mMessageList = mMessageList;
    }


    //@NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.message_single_layout, viewGroup, false);
        return new MessageViewHolder(v);
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public CircleImageView profileImage;

        public MessageViewHolder(final View itemView) {
            super(itemView);

            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Get an instance of KeyguardManager and FingerprintManager//
                keyguardManager =
                        (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
                fingerprintManager =(FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            //textView = (TextView) findViewById(R.id.textview);

            //Check whether the device has a fingerprint sensor//
            if (!fingerprintManager.isHardwareDetected()) {
                // If a fingerprint sensor isn’t available, then inform the user that they’ll be unable to use your app’s fingerprint functionality//
                textView.setText("Your device doesn't support fingerprint authentication");
            }
            //Check whether the user has granted your app the USE_FINGERPRINT permission//
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // If your app doesn't have this permission, then display the following text//
                textView.setText("Please enable the fingerprint permission");
            }

            //Check that the user has registered at least one fingerprint//
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                // If the user hasn’t configured any fingerprints, then display the following message//
                textView.setText("No fingerprint configured. Please register at least one fingerprint in your device's Settings");
            }

            //Check that the lockscreen is secured//
            if (!keyguardManager.isKeyguardSecure()) {
                // If the user hasn’t secured their lockscreen with a PIN password or pattern, then display the following text//
                textView.setText("Please enable lockscreen security in your device's Settings");
            } else {
                try {*/

            messageText = itemView.findViewById(R.id.message_text_layout);
            profileImage = itemView.findViewById(R.id.message_profile_layout);

            messageText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String messageTemp = messageText.getText().toString();
                    Decryption(messageTemp);
                    messageText.setText(decrypted);
                    //Toast.makeText(itemView.getContext(), decrypted, Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            messageText.setText(messageTemp);
                        }
                    },5000);
                    decrypted = "";
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {

        mAuth = FirebaseAuth.getInstance();
        String current_user_id = mAuth.getCurrentUser().getUid();
        Messages c = mMessageList.get(i);
        String from_user = c.getFrom();

        if(!from_user.equals(current_user_id)) {
            messageViewHolder.messageText.setBackgroundResource(R.drawable.message_text_right);
            messageViewHolder.messageText.setTextColor(Color.BLACK);
        }
        else {
            messageViewHolder.messageText.setBackgroundResource(R.drawable.message_text_background);
            messageViewHolder.messageText.setTextColor(Color.WHITE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)messageViewHolder.messageText.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            messageViewHolder.messageText.setLayoutParams(params);

            messageViewHolder.profileImage.setVisibility(View.GONE);
        }
        messageViewHolder.messageText.setText(c.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
