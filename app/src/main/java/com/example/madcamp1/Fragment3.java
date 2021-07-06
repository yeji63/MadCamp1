package com.example.madcamp1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madcamp1.one.OneActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class Fragment3 extends Fragment implements View.OnClickListener{

    private static final String TAG = "exception 정수형 바꾸기";
    ImageView gameimg;
    int selectimg;
    Bitmap bitmap;

    //CAMERAAAAAAAAAAAA
    //final String TAG = getClass().getSimpleName();
    Button camera;
    final static int TAKE_PICTURE = 1;

    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    //CAMEREEEEEEEE


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        //CAMERAA
        camera = (Button) view.findViewById(R.id.camera);
        // 카메라 버튼에 리스너 추가
        camera.setOnClickListener(this);
        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
                Log.d(TAG, "권한 설정 완료");
            } else {
                Log.d(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        //CAMEREE
        Button btn = (Button) view.findViewById(R.id.gamebtn);
        gameimg = (ImageView) view.findViewById(R.id.gameimg);
        gameimg.setImageResource(R.drawable.ggg);

        gameimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), GalleryDetailActivity.class);
                i.putExtra("frag", 2);
                startActivityForResult(i, 2);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    EditText et = (EditText)view.findViewById(R.id.et_num);
                    int num = Integer.parseInt(et.getText().toString());
                    Intent i = new Intent(getContext(), OneActivity.class);
                    i.putExtra("n_num", num);

                    if(selectimg==0 && bitmap==null){
                        i.putExtra("imgres", R.drawable.ggg);
                        Toast.makeText(getContext(),"기본 이미지로 설정됩니다.", Toast.LENGTH_LONG).show();
                    }else if (bitmap == null){
                        i.putExtra("imgres", selectimg);
                    } else if (selectimg==0){
                        //bitmap을 resize
                        bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                        i.putExtra("imgbtm", bitmap);
                    }
                    startActivity(i);
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(),"정수형으로 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if(data != null){
                selectimg= data.getIntExtra("selectimg", 0);
                bitmap = null;
                //String name = data.getStringExtra("name");
                gameimg.setImageResource(selectimg);
            }
        }

        // 카메라로 촬영한 영상을 가져오는 부분
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    if (resultCode == RESULT_OK) {
                        File file = new File(mCurrentPhotoPath);
                        bitmap = MediaStore.Images.Media
                                .getBitmap(getActivity().getContentResolver(), Uri.fromFile(file));
                        selectimg = 0;
                        if (bitmap != null) {
                            gameimg.setImageBitmap(bitmap);
                            //여기서 array에 추가?
//                            Intent i = new Intent(getContext(), OneActivity.class);
//                            i.putExtra("imgbtm", bitmap);
                        }
                    }
                    break;
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.camera:
                // 카메라 앱을 여는 소스
                dispatchTakePictureIntent();
                break;
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    //카메라 인텐트 실행
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.madcamp1.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

}