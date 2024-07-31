package com.alpharishi.openinappasspackage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alpharishi.openinappassighnmentrushikesh.R
import com.alpharishi.openinappassighnmentrushikesh.adapters.RecentLinkRecyclerViewAdapter
import com.alpharishi.openinappassighnmentrushikesh.model.MainDataClass
import com.alpharishi.openinappassighnmentrushikesh.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentLinksFragment : Fragment() {

    private lateinit var adapter : RecentLinkRecyclerViewAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recent_links, container, false)

        recyclerView = view.findViewById(R.id.rvRecentLinks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getData()

        // Inflate the layout for this fragment
        return view
    }



    private fun getData(){
        RetrofitInstance.apiService.getData().enqueue(object : Callback<MainDataClass?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<MainDataClass?>,
                response: Response<MainDataClass?>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        adapter = RecentLinkRecyclerViewAdapter(data.data.recent_links)
                        recyclerView.adapter = adapter
                    }
                }
                else {
                    Toast.makeText(requireContext(),"Response is unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MainDataClass?>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}