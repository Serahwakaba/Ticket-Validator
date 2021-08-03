package com.example.ticketvalidator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URLstring= "http://35.187.164.231/ticket-bay-api/v1/api/event/view";
    private RecyclerView mEvents;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<TicketEvent> eventList;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        //Declaring the variables
        mEvents = findViewById(R.id.main_list);

        eventList = new ArrayList<>();
        adapter = new TicketEventAdapter(getApplicationContext(), eventList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mEvents.getContext(), linearLayoutManager.getOrientation());

        mEvents.setHasFixedSize(true);
        mEvents.setLayoutManager(linearLayoutManager);
        mEvents.addItemDecoration(dividerItemDecoration);
        mEvents.setAdapter(adapter);
        JSONObject jsonObject = new JSONObject();
        getData(jsonObject);
    }


            private void getData(JSONObject payload) {


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "http://35.187.164.231/ticket-bay-api/v1/api/event/view",
                    payload, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    Log.d("st", ">>" + response);


                    try{
                        JSONObject data = response.getJSONObject("data");


                        for (int i = 0; i < data.length(); i++) {

                              JSONArray datas = data.getJSONArray(("data"));
                            Log.d("datas", datas.toString());


                            for (int j = 0; j < datas.length(); i++) {
                                  JSONObject jsonObject = datas.getJSONObject(j);
                                  Log.d("data", datas.toString());

                                  TicketEvent ticketEvent = new TicketEvent();
                                  ticketEvent.setName(jsonObject.getString("eventName"));
                                  ticketEvent.setDate(jsonObject.getString("start_date"));
                                  ticketEvent.setTotal(jsonObject.getString("total"));
                                  ticketEvent.setTickets(jsonObject.getString("company"));
                                  eventList.add(ticketEvent);

                              }

                        }
                        adapter.notifyDataSetChanged();

                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

}
            }, volleyError -> {
//            progressDialog.dismiss();
                        NetworkResponse response = volleyError.networkResponse;

                        // MotionTelltales lblMessageBox = null;
                        if (response != null && response.data != null) {
                            String json = new String(response.data);
                            Log.d("Error", json);

                            try {
                                JSONObject obj = new JSONObject(json);
                                json = obj.getString("statusDescription");
                                if (!obj.isNull("data")) {
                                    JSONObject data = obj.getJSONObject("data");
                                    Toast.makeText(getApplicationContext(), data.getString("message"), Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {

                                Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();

                        }

                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjReq);
            RetryPolicy policy = new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjReq.setRetryPolicy(policy);
            // Adding request to request queue
            RequestSingletone.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, "data");
        }
}



