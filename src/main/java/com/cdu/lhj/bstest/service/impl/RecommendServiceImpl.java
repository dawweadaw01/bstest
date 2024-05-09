package com.cdu.lhj.bstest.service.impl;

import com.cdu.lhj.bstest.pojo.Vo.ScoreVo;
import com.cdu.lhj.bstest.service.RecommendService;
import com.cdu.lhj.bstest.service.ScoreService;
import jakarta.annotation.Resource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private ScoreService scoreService;

    @Override
    public List<Long> recommend(Long userId) throws TasteException {

        List<ScoreVo> userList = scoreService.getAllUserPreference();
        //创建数据模型
        DataModel dataModel = this.createDataModel(userList);
        //获取用户相似程度
        UserSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
        //获取用户邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //构建推荐器
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //推荐2个
        List<RecommendedItem> recommendedItems = recommender.recommend(userId, 6);
        List<Long> itemIds = recommendedItems.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
        return itemIds;
    }

    private DataModel createDataModel(List<ScoreVo> userArticleOperations) {
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
        Map<Long, List<ScoreVo>> map = userArticleOperations.stream().collect(Collectors.groupingBy(ScoreVo::getUserId));
        Collection<List<ScoreVo>> list = map.values();
        for(List<ScoreVo> userPreferences : list){
            GenericPreference[] array = new GenericPreference[userPreferences.size()];
            for(int i = 0; i < userPreferences.size(); i++){
                ScoreVo userPreference = userPreferences.get(i);
                GenericPreference item = new GenericPreference(userPreference.getUserId(), userPreference.getStoreId(), userPreference.getScore());
                array[i] = item;
            }
            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
        }
        return new GenericDataModel(fastByIdMap);
    }

}
