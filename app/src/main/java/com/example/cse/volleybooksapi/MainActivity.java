package com.example.cse.volleybooksapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cse.volleybooksapi.adapters.BookAdapter;
import com.example.cse.volleybooksapi.model.BookModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String url="https://www.googleapis.com/books/v1/volumes?q=java";
    String book_title,book_author,book_image,book_des;
    ArrayList<BookModel> arrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue=Volley.newRequestQueue(this);
        arrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                parseData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest);
    }
    public void parseData(String response){
        try {
            JSONObject root=new JSONObject(response);
            JSONArray items=root.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject object=items.getJSONObject(i);
                JSONObject volumeInfo=object.getJSONObject("volumeInfo");
                book_title=volumeInfo.optString("title");
                book_des=volumeInfo.optString("description");
                JSONArray authors=volumeInfo.getJSONArray("authors");
                book_author=authors.getString(0);
                JSONObject imageLinks=volumeInfo.getJSONObject("imageLinks");
                book_image=imageLinks.getString("thumbnail");
                //Toast.makeText(this, book_author+book_title+book_des+book_image, Toast.LENGTH_SHORT).show();
                BookModel bookModel=new BookModel(book_author,book_title,book_image,book_des);
                arrayList.add(bookModel);
                BookAdapter adapter=new BookAdapter(MainActivity.this,arrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
