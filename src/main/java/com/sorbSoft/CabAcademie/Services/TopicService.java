package com.sorbSoft.CabAcademie.Services;


import com.sorbSoft.CabAcademie.Entities.Category;
import com.sorbSoft.CabAcademie.Entities.Topic;
import com.sorbSoft.CabAcademie.Repository.CategoryRepository;
import com.sorbSoft.CabAcademie.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dany on 18/05/2018.
 */
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  CategoryService categoryService;

    public List<Topic> fetchAllTopic(){
        return topicRepository.findAll();
    }

    public Topic fetchTopic(Long id){
        return topicRepository.findOne(id);
    }

    public Topic updateTopic(Topic topic){
        Topic currentTopic= topicRepository.findOne(topic.getId());
        currentTopic.setCategory(topic.getCategory());
        currentTopic.setNombre(topic.getNombre());
        return topicRepository.save(currentTopic);
    }
    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }


    public boolean saveTopics (List<Topic> topics){

        if(topics.size()==0)
            return  false;

        if(topics.get(0).getCategory().getId()!=0){
            Category category = categoryRepository.findOne(topics.get(0).getCategory().getId());
            if(category!=null){

               checkTopics(category,topics );

            }else{
                //peu probable
                Category currentCategory = categoryRepository.save(topics.get(0).getCategory());
                checkTopics(currentCategory, topics);
            }
        }
        else{

            Category currentCategory = categoryRepository.save(topics.get(0).getCategory());
            for(Topic topic: topics){
                topic.setCategory(currentCategory);
                topicRepository.save(topic);
            }
        }

       return true;
    }

    private void checkTopics(Category category, List<Topic> topics){
        for(Topic topic: topics){
            if(topic.getId()==0){
                topic.setCategory(category);
                topicRepository.save(topic);
            }else{
                updateTopic(topic);
                categoryService.updateCategory(topics.get(0).getCategory());
            }
        }
    }

    public void deleteTopic(Long id){
        topicRepository.delete(id);
    }
    //other delete methods
    //other fetching methods
}
