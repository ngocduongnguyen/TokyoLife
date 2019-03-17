package com.duong.tokyolife.View.DangNhap_DangKy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.duong.tokyolife.Model.TrangChu.ModelDangNhap;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    View view;
    ImageView btnDangNhapFB,btnDangNhapGG;
    Button btnDangNhap;
    EditText edUsername,edPassword;
    //facebook
    CallbackManager callbackManager;
    //google account
    GoogleApiClient googleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 1998;
    //database
    ModelDangNhap modelDangNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dangnhap,container,false);
        addControls();
        addEvents();
        return view;
    }

    private void addControls() {
        //Google
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        googleApiClient=modelDangNhap.layGoogleAPIClient(getContext(),this);
        //Facebook
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
    //            Log.d("kiemtra","Thành công!");
                Intent iTrangChu = new Intent(getActivity(), MainActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {
    //            Log.d("kiemtra","Thoát!");
            }

            @Override
            public void onError(FacebookException error) {
    //            Log.d("kiemtra","Lỗi!");
            }
    });

    btnDangNhapFB = view.findViewById(R.id.btnDangNhapFB);
    btnDangNhapGG = view.findViewById(R.id.btnDangNhapGG);
    btnDangNhap = view.findViewById(R.id.btnDangNhap);
    edUsername = view.findViewById(R.id.edEmailDangNhap);
    edPassword = view.findViewById(R.id.edPassDangNhap);

    }

    private void addEvents() {

    btnDangNhapFB.setOnClickListener(this);
    btnDangNhapGG.setOnClickListener(this);
    btnDangNhap.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangNhapFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGG:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(iGooglePlus,SIGN_IN_GOOGLE_PLUS);
                break;
            case R.id.btnDangNhap:
                modelDangNhap = new ModelDangNhap();
                boolean kt = modelDangNhap.kiemTraDangNhap(getActivity(),edUsername.getText().toString().trim(),edPassword.getText().toString().trim());

                if (kt){
                    Intent iTrangChu = new Intent(getActivity(),MainActivity.class);
                    startActivity(iTrangChu);
                } else {
                    Toast.makeText(getContext(),"Username or password erorr!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode==SIGN_IN_GOOGLE_PLUS){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            Log.d("googlePlus",googleSignInResult.getSignInAccount().getDisplayName());
            if (googleSignInResult.isSuccess()){
                Intent iTrangChu = new Intent(getActivity(),MainActivity.class);
                startActivity(iTrangChu);
            }
        }
    }


    // Ket noi loi cua google acc
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    //code lấy HashKey Facebook
//    try {
//        PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                "com.duong.tokyolife",
//                PackageManager.GET_SIGNATURES);
//        for (Signature signature : info.signatures) {
//            MessageDigest md = MessageDigest.getInstance("SHA");
//            md.update(signature.toByteArray());
//            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//        }
//    } catch (PackageManager.NameNotFoundException e) {
//
//    } catch (NoSuchAlgorithmException e) {
//
//    }
}
