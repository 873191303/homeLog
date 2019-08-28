package com.jitv.tv.util;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.util.JsonUtil;

/**
 * 全聚合数据
 */
public abstract class FstData {
	
	private final Object[] args;//url arguments
	
	public FstData(Object[] urlPatternArgs) throws Exception {
		args = urlPatternArgs;
	}
	
	protected void setArg(int index, Object o){
		args[index] = o;
	}
	
	protected Object getArg(int index){
		return args[index];
	}
	
	protected Map<String, Object> getRootMap() throws Exception{
		String url = MessageFormat.format(defaultURLPattern(), args);
//		String json = UrlConnectionHelper.connect(url, Integer.MAX_VALUE, TimeUnit.MILLISECONDS.toMillis(200));
		String json = null;
		do{
			try {
				json = HttpClientHelper.getInstance().get(url);
			} catch (Exception e) {
				//ignore
			}
			
			Thread.sleep(100);
		}while(json == null);
		
		return JsonUtil.toBean(json, Map.class);
//		return GSON.fromJson(json, Map.class);
	}
	
	/**
	 * 数据抓取 
	 */
	public abstract Object get() throws Exception;
	protected abstract String defaultURLPattern();
	
	public static final VideoList newVideoList(int topId, int pageNo) throws Exception{
		return new VideoList(topId, pageNo);
	}
	public static final VideoInfo newVideoInfo(String uuid) throws Exception{
		return new VideoInfo(uuid);
	}
	public static final VolumeList newVolumeList(String uuid) throws Exception{
		return new VolumeList(uuid);
	}
	public static final ActorList newActorList(String uuid) throws Exception{
		return new ActorList(uuid);
	}
	
	/**
	 * 影片列表
	 */
	public static final class VideoList extends FstData{
		private VideoList(int topId, int pageNo) throws Exception{
			super(new Object[]{topId, pageNo});
		}

		@Override
		public Map<String, Object> get() throws Exception {
			return getRootMap();
		}

		@Override
		protected String defaultURLPattern() {
			return "http://api3.91vst.com/api3.0/videolist.action?topID={0}&pageNo={1}"
					+ "&sort=0&area=all&quality=all&type=all&year=all";
		}
	}
	
	/**
	 * 影片详情
	 */
	public static final class VideoInfo extends FstData{

		private VideoInfo(String uuid) throws Exception {
			super(new Object[]{uuid});
		}

		@SuppressWarnings("unchecked")
		@Override
		public Map<String, Object> get() throws Exception {
			Map<String, Object> rootMap = getRootMap();
			return (Map<String, Object>) rootMap.get("data");
		}

		@Override
		protected String defaultURLPattern() {
			return "http://api3.91vst.com/api3.0/videoinfo.action?uuid={0}";
		}
	}
	
	/**
	 * 影片剧集
	 */
	public static final class VolumeList extends FstData{
		
		private VolumeList(String uuid) throws Exception{
			super(new Object[]{uuid, 1});
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> get() throws Exception {
			Map<String, Object> rootMap = getRootMap();
			
			List<Map<String, Object>> data = (List<Map<String, Object>>) rootMap.get("data");
			Map<String,Object> info = (Map<String, Object>) rootMap.get("info");
			
			int totalPages = ((Number) info.get("totalPages")).intValue();
			int currPage = (int) getArg(1);
			if (currPage < totalPages) {
				setArg(1, currPage + 1);
				data.addAll(get());
			}
			
			return data;
		}
		
		@Override
		protected String defaultURLPattern() {
			return "http://api3.91vst.com/api3.0/videovolume.action?uuid={0}&pageNo={1}";
		}
	}
	
	/**
	 * 演员
	 */
	public static final class ActorList extends FstData{
		
		public ActorList(String uuid) throws Exception{
			super(new Object[]{uuid});
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> get() throws Exception {
			Map<String, Object> rootMap = getRootMap();
			return (List<Map<String, Object>>) rootMap.get("data");
		}
		
		@Override
		protected String defaultURLPattern() {
			return "http://cdn.91vst.com/api3.0/videoactor.action?uuid={0}";
		}
	}
	
}
