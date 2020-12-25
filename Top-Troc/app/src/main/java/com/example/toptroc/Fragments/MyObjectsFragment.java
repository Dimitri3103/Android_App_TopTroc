package com.example.toptroc.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toptroc.Adaptaters.ObjectAdaptater;
import com.example.toptroc.HomeActivity;
import com.example.toptroc.Models.ObjectModel;
import com.example.toptroc.ObjectDepositActivity;
import com.example.toptroc.R;
import com.example.toptroc.SQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyObjectsFragment extends Fragment {


    public MyObjectsFragment() {
        // Required empty public constructor
    }

    private View view;
    GridView gridViewMyObjects;
    ArrayList<ObjectModel> objectList;
    ObjectAdaptater objectAdaptater = null;
    public static SQLiteHelper sqLiteHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_objects, container, false);

        sqLiteHelper = new SQLiteHelper(getActivity());

        gridViewMyObjects = (GridView) view.findViewById(R.id.gridViewMyObjects);
        objectList = new ArrayList<>();
        objectAdaptater = new ObjectAdaptater(getContext(),R.layout.layout_object_item,objectList);
        gridViewMyObjects.setAdapter(objectAdaptater);

        Cursor cursor = HomeActivity.sqLiteHelper.getData();
        objectList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            objectList.add(new ObjectModel(id,name,description,image));
        }
        objectAdaptater.notifyDataSetChanged();

        gridViewMyObjects.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] items = {"Modifier","Supprimer"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                dialog.setTitle("Choisir une action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0){
                            //Modifier
                            Cursor c = HomeActivity.sqLiteHelper.getData();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(getActivity(),arrID.get(position));
                        }else{
                            //Supprimer
                            Cursor c = HomeActivity.sqLiteHelper.getData();
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        return view;
    }

    private void showDialogDelete(int idObject){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(getContext());

        dialogDelete.setTitle("Attention!!!");
        dialogDelete.setMessage("Etes vous sur de vouloir supprimer ??");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    HomeActivity.sqLiteHelper.deleteData(idObject);
                    Toast.makeText(getActivity().getApplicationContext(),"Suppression Réussie  ...",Toast.LENGTH_SHORT).show();
                }catch (Exception error){
                    Log.e("Erreur!!", error.getMessage());
                }
                updateObjectList();
            }
        });
        dialogDelete.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    ImageView editImageObject;
    private void showDialogUpdate(Activity activity,final int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.layout_update_object);
        dialog.setTitle("Modifier");

        editImageObject = (ImageView) dialog.findViewById(R.id.editImageObject);
        EditText editNameObject = (EditText) dialog.findViewById(R.id.editNameObject);
        EditText editDescriptionObject = (EditText) dialog.findViewById(R.id.editDescriptionObject);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);

        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.8);

        dialog.getWindow().setLayout(width,height);
        dialog.show();

        editImageObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HomeActivity.sqLiteHelper.updateData(
                            editNameObject.getText().toString().trim(),
                            editDescriptionObject.getText().toString().trim(),
                            imageViewtoByte(editImageObject),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getActivity().getApplicationContext(),"Modification Réussie  ...",Toast.LENGTH_SHORT).show();

                }catch (Exception error){
                    Log.e("Erreur!!", error.getMessage());
                }
                updateObjectList();
            }
        });
    }

    private byte[] imageViewtoByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 888){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,888);
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(),"Vous n'avez pas la permission d'accéder aux images", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 888 && resultCode == getActivity().RESULT_OK && data != null){
            Uri uri= data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                editImageObject.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateObjectList() {
        Cursor cursor = HomeActivity.sqLiteHelper.getData();
        objectList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            objectList.add(new ObjectModel(id, name, description, image));
        }
        objectAdaptater.notifyDataSetChanged();
    }

}