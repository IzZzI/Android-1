package com.example.fragmentbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment {
	
	private ListView newsTitleListView;
	private List<News> newsList;
	private NewsAdapter newsAdapter;
	private boolean isTwoPane;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		newsList = getNews();
		newsAdapter = new NewsAdapter(activity, R.layout.news_item, newsList);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.news_title_frag, container, false);
		newsTitleListView = (ListView)view.findViewById(R.id.news_title_list_view);
		newsTitleListView.setAdapter(newsAdapter);
		newsTitleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				News news = newsList.get(position);
				if(isTwoPane){
					//˫ҳģʽ
					NewsContentFragment newsContentFragment = (NewsContentFragment)
							getFragmentManager().findFragmentById(R.id.news_content_fragment);
					newsContentFragment.refresh(news.getTitle(), news.getContent());
				}else {
					//��ҳģʽ
					NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
				}
			}
			
		});
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if(getActivity().findViewById(R.id.news_content_layout) != null){
			isTwoPane = true;
		}else {
			isTwoPane = false;
		}
	}
	
	private List<News> getNews(){
		List<News> newsList = new ArrayList<News>();
		News news1 = new News();
		news1.setTitle("��ý��������ϵ��ˮ���� ������߽类����");
		news1.setContent("�ο���Ϣ��3��14�ձ��� ��ý�ƣ�������Ͷ���˹����֮���ϵ��2015��11��"
				+ "ǰ�߿վ�����һ�ܶ����-24��㼱תֱ�¡�����������Ź�ϵ�ڴ�ǰ��һֱ��������"
				+ "�������������˹��Ԥ�ڿ�����֮����Ϊ���������⡣��ˣ���2��ʱ���"
				+ "�����������Ѵݻٹ�ȥ15�꽨������½��ϵ��");
		newsList.add(news1);
		News news2 = new News();
		news2.setTitle("�¹��������ݷ���� �վ���������");
		news2.setContent("����3��13�գ��¹���������򣬵��ؾ��С�õ������һ�����У�"
				+ "���з����ζ�ġ���ż��������һ�����㣬�����Ҫ���С�");
		newsList.add(news2);
		return newsList;
	}
}










