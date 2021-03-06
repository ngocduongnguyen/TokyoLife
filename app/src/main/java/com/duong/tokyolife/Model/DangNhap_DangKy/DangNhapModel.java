package com.duong.tokyolife.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DangNhapModel {

    //Token Facebook
    AccessToken accessToken = null;
    AccessTokenTracker accessTokenTracker;
    public AccessToken layTokenFBHienTai(){
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken=currentAccessToken;
            }
        };
        accessToken=AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public void huyTokenFacebook(){
        accessTokenTracker.stopTracking();
    }


    //lay Google API Client
    public GoogleApiClient layGoogleAPIClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return googleApiClient;
    }
    public GoogleSignInResult layThongTinDangNhapGG(GoogleApiClient googleApiClient){

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone())
        {
            return opr.get();
        } else {
            return null;
        }
    }


    //Dang nhap su dung acc trong database
    public boolean kiemTraDangNhap(Context context,String username,String password){
        boolean kiemTra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> goiHam = new HashMap<>();
        goiHam.put("goiham","kiemTraDangNhap");

        HashMap<String,String> hmUsername = new HashMap<>();
        hmUsername.put("tendn",username);

        HashMap<String,String> hmPassword = new HashMap<>();
        hmPassword.put("matkhau",password);

        attrs.add(goiHam);
        attrs.add(hmUsername);
        attrs.add(hmPassword);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            String kt = jsonObject.getString("ketqua");
            if (kt.equals("true")){

                String tennv = jsonObject.getString("tennv");
                updateCacheDangNhapDatabase(context,tennv);

                String manv = jsonObject.getString("manv");
                // lấy mã nhân viên, tên nhân viên
                Data.code=manv;
                Data.name=tennv;

                kiemTra=true;
            } else {
                kiemTra=false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemTra;
    }

    public String layCacheDangNhapDatabase(Context context){

        SharedPreferences cacheDangNhapDatabase = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tenNV = cacheDangNhapDatabase.getString("tennv","");

        return tenNV;

    }

    public void updateCacheDangNhapDatabase(Context context,String tennv){
        SharedPreferences cacheDangNhapDatabase = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cacheDangNhapDatabase.edit();
        editor.putString("tennv",tennv);
        editor.commit();
    }

    public void themAccGG_FB(String _id, String _name){
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","themAccFB_GG");

        HashMap<String,String> id = new HashMap<>();
        id.put("id",_id);

        HashMap<String,String> name = new HashMap<>();
        name.put("name",_name);

        Collections.addAll(attrs,goiham,id,name);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();
    }

    public String code(String _name){
        String code = new String();

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","layCode");

        HashMap<String,String> name = new HashMap<>();
        name.put("name",_name);

        Collections.addAll(attrs,goiham,name);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        String dataJson = null;
        try {
            dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            code = jsonObject.getString("code");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return code;
    }
}
