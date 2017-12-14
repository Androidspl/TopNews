package com.topnews.tool;

import java.util.ArrayList;
import java.util.List;

import com.topnews.activity.ChannelActivity;
import com.topnews.bean.DataBean;
import com.topnews.bean.NewsClassify;
import com.topnews.bean.NewsEntity;

public class Constants {
	public static ArrayList<NewsClassify> getData() {
		ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
		NewsClassify classify = new NewsClassify();
		classify.setId(0);
		classify.setTitle("推荐");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(1);
		classify.setTitle("热点");
		newsClassify.add(classify);

//		ChannelActivity channelActivity = new ChannelActivity();
//		List<DataBean> channleList = channelActivity.channleList;
//		for (int i=0; i<channleList.size(); i++) {
//			NewsClassify classify_ = new NewsClassify();
//			classify.setTitle(channleList.get(i).getName());
//			newsClassify.add(classify_);
//		}

//		classify.setId(0);
//		classify.setTitle("推荐");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(1);
//		classify.setTitle("热点");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(2);
//		classify.setTitle("数码");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(3);
//		classify.setTitle("杭州");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(4);
//		classify.setTitle("社会");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(5);
//		classify.setTitle("娱乐");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(6);
//		classify.setTitle("科技");
//		newsClassify.add(classify);
//		classify = new NewsClassify();
//		classify.setId(7);
//		classify.setTitle("汽车");
//		newsClassify.add(classify);
		return newsClassify;
	}

	public static ArrayList<NewsEntity> getNewsList() {
		ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
		for(int i =0 ; i < 10 ; i++){
			NewsEntity news = new NewsEntity();
			news.setId(i);
			news.setNewsId(i);
			news.setCollectStatus(false);
			news.setCommentNum(i + 10);
			news.setInterestedStatus(true);
			news.setLikeStatus(true);
			news.setReadStatus(false);
			news.setNewsCategory("推荐");
			news.setNewsCategoryId(1);
			news.setTitle("可以用谷歌眼镜做的10件酷事：导航、玩游戏");
			List<String> url_list = new ArrayList<String>();
			if(i%2 == 1){
//				String url1 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066094_400_640.jpg";
//				String url2 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066096_400_640.jpg";
//				String url3 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066099_400_640.jpg";
				String url1 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1577735516,2735029391&fm=27&gp=0.jpg";
				String url2 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1689474615,1892448225&fm=27&gp=0.jpg";
				String url3 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3118373368,72267147&fm=27&gp=0.jpg";
				news.setPicOne(url1);
				news.setPicTwo(url2);
				news.setPicThr(url3);
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
			}else{
				news.setTitle("AA用车:智能短租租车平台");
//				String url = "http://r3.sinaimg.cn/2/2014/0417/a7/6/92478595/580x1000x75x0.jpg";
				String url = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3611920610,3263649367&fm=27&gp=0.jpg";
				news.setPicOne(url);
				url_list.add(url);
			}
			news.setPicList(url_list);
			news.setPublishTime(Long.valueOf(i));
			news.setReadStatus(false);
			news.setSource("手机腾讯网");
			news.setSummary("腾讯数码讯（编译：Gin）谷歌眼镜可能是目前最酷的可穿戴数码设备，你可以戴着它去任何地方（只要法律法规允许或是没有引起众怒），作为手机的第二块“增强现实显示屏”来使用。另外，虽然它仍未正式销售，但谷歌近日在美国市场举行了仅限一天的开放购买活动，价格则为1500美元（约合人民币9330元），虽然仍十分昂贵，但至少可以满足一些尝鲜者的需求，也预示着谷歌眼镜的公开大规模销售离我们越来越近了。");
			news.setMark(i);
			if(i == 4){
				news.setTitle("部落战争强势回归");
				news.setLocal("推广");
				news.setIsLarge(true);
//				String url = "http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg";
//				String url = "https://s3m.mediav.com/galileo/91ee6fcb636466da093b53dab74df091.jpg";
				String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512712886368&di=4d4aa32eb4cce61185bc07cedef9b766&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fblog%2F201406%2F07%2F20140607180614_idtaF.jpeg";
				news.setPicOne(url);
				url_list.clear();
				url_list.add(url);
			}else{
				news.setIsLarge(false);
			}
			if(i == 2){
				news.setComment("评论部分，说的非常好。");
			}
			newsList.add(news);
		}
		return newsList;
	}

	/** mark=0 ：推荐 */
	public final static int mark_recom = 0;
	/** mark=1 ：热门 */
	public final static int mark_hot = 1;
	/** mark=2 ：首发 */
	public final static int mark_frist = 2;
	/** mark=3 ：独家 */
	public final static int mark_exclusive = 3;
	/** mark=4 ：收藏 */
	public final static int mark_favor = 4;
}
