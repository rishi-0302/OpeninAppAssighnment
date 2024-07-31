package com.alpharishi.openinappassighnmentrushikesh.view



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
import com.alpharishi.openinappassighnmentrushikesh.adapters.TopLinkRecyclerViewAdapter
import com.alpharishi.openinappassighnmentrushikesh.model.MainDataClass
import com.alpharishi.openinappassighnmentrushikesh.network.RetrofitInstance
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

private lateinit var adapter : TopLinkRecyclerViewAdapter
private lateinit var recyclerView : RecyclerView

class TopLinksFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_links, container, false)

        recyclerView = view.findViewById(R.id.rvTopLinks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getData()

        // Inflate the layout for this fragment
        return view
    }

    private fun getData(){
        RetrofitInstance.apiService.getData().enqueue(object : retrofit2.Callback<MainDataClass?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: retrofit2.Call<MainDataClass?>,
                response: retrofit2.Response<MainDataClass?>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        adapter = TopLinkRecyclerViewAdapter(data.data.top_links)
                        recyclerView.adapter = adapter
                    }
                }
                else {
                    Toast.makeText(requireContext(),"Response is unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<MainDataClass?>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}