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
					//双页模式
					NewsContentFragment newsContentFragment = (NewsContentFragment)
							getFragmentManager().findFragmentById(R.id.news_content_fragment);
					newsContentFragment.refresh(news.getTitle(), news.getContent());
				}else {
					//单页模式
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
		news1.setTitle("外媒：俄土关系已水火不容 土耳其边界被打破");
		news1.setContent("参考消息网3月14日报道 外媒称，土耳其和俄罗斯两国之间关系自2015年11月"
				+ "前者空军击落一架俄军苏-24后便急转直下。但两国间紧张关系在此前就一直不断升级"
				+ "，首先是因俄罗斯干预乌克兰，之后因为叙利亚问题。因此，在2年时间里，"
				+ "两国基本上已摧毁过去15年建立起的谅解关系。");
		newsList.add(news1);
		News news2 = new News();
		news2.setTitle("德国游行上演讽刺秀 普京川普中招");
		news2.setContent("当地3月13日，德国杜塞尔多夫，当地举行“玫瑰星期一”游行，"
				+ "带有讽刺意味的“玩偶”花车成一大亮点，多国政要中招。");
		newsList.add(news2);
		return newsList;
	}
}










