package com.edu.baogia.introducefood.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.NewsAdapter;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerNews;
import com.edu.baogia.introducefood.interfaces.NewsMVP;
import com.edu.baogia.introducefood.model.mySQL.NewsModel;
import com.edu.baogia.introducefood.model.object.News;
import com.edu.baogia.introducefood.presenter.NewsPresenter;
import com.edu.baogia.introducefood.view.activity.NewsInfoActivity;


import java.util.List;

public class FragmentNews extends Fragment implements NewsMVP.View {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    NewsMVP.Presenter presenter;
    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        recyclerView=view.findViewById(R.id.listNews);
        searchView=view.findViewById(R.id.searchView);
        searchView.setMaxWidth(Integer.MAX_VALUE
        );
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Đang tải");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        presenter=new NewsPresenter(this,new NewsModel(getContext()));
        presenter.getList();
        return view;
    }

    @Override
    public void setList(List<News> list) {
        NewsAdapter newsAdapter=new NewsAdapter(getContext(), list, R.layout.line_news, new ItemClickListenerNews() {
            @Override
            public void itemClickListener(News news) {
                Intent intent=new Intent(getContext(), NewsInfoActivity.class);
                intent.putExtra("Link",news.getLink());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(newsAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        progressDialog.dismiss();
    }

    @Override
    public void onFail() {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "Không có tin tức", Toast.LENGTH_SHORT).show();
    }
}
