package com.example.wati.laravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wati.laravel.adapter.MahasiswaAdapter;
import com.example.wati.laravel.model.Mahasiswa;
import com.example.wati.laravel.model.MahasiswaResult;
import com.example.wati.laravel.network.ApiClient;
import com.example.wati.laravel.network.MahasiswaService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. Menyiapkan sumber data
        //1. a. array string 1 dimensi
        /*String[] user = new String[]{"Wati","fitri","lutfi"};*/
        /*ArrayList<String> user = new ArrayList<>();
        user.add("User 1");
        user.add("User 2");
        user.add("User 3");
        user.add("User 4"); */

        /*Picasso.get()
                .load("https://picsum.photos/200/300/?random")
                .into()*/

        //1. b. POJO (lebih dari 1 dimensi)
       /* Mahasiswa mahasiswa1 = new Mahasiswa();
        mahasiswa1.setNama("fitri");
        mahasiswa1.setNim("3.34.15.0.08");
        mahasiswa1.setEmail("fitri@gmail.com");
        mahasiswa1.setFoto("https://picsum.photos/200/300/?random");

        Mahasiswa mahasiswa2 = new Mahasiswa(
                "wati",
                "3.34.15.0.12",
                "wati@gmail.com",
                "https://picsum.photos/200/300/?random"
        );

        data = new ArrayList<>();
        data.add(mahasiswa1);
        data.add(mahasiswa2); */

        //1.3. Sumber data dari API / JSON Object dari internet (retrofit)

        MahasiswaService mService = ApiClient.getRetrofit()
                .create(MahasiswaService.class);

        Call<MahasiswaResult> mahasiswas = mService.getMahasiswas();
        mahasiswas.enqueue(new Callback<MahasiswaResult>() {
            @Override
            public void onResponse(Call<MahasiswaResult> call, Response<MahasiswaResult> response) {
                tampilkan(response.body().getMahasiswas());
                Toast.makeText(
                        getApplicationContext(),
                        "jumlah Data : "+ response.body().getMahasiswas().size(),
                        Toast.LENGTH_LONG
                ).show();
            }

            @Override
            public void onFailure(Call<MahasiswaResult> call, Throwable t) {

            }
        });
    }

    private void tampilkan(List<Mahasiswa> mahasiswas){
        //2. Menyiapkan adapter

       MahasiswaAdapter userAdapter = new MahasiswaAdapter(this,0,mahasiswas);

        //3. Tampilan Aplikasi (Listview, Gridview, Recyclerview)
        ListView lvUser = (ListView) findViewById(R.id.lv_user);
        lvUser.setAdapter(userAdapter);
    }

    //muat gambar
}
