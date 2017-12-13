package com.example.mooclistService;


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//----------------------------------------------- Use JSON request/response
@RestController
@RequestMapping("/")
public class MoocListServiceController {

    private MoocEntityRepository moocEntityRepository;
    private TagEntityRepository tagEntityRepository;

    @Autowired
    public MoocListServiceController(MoocEntityRepository moocEntityRepository, TagEntityRepository tagEntityRepository) {
        this.moocEntityRepository = moocEntityRepository;
        this.tagEntityRepository = tagEntityRepository;
    }


    @RequestMapping(value = "/fail")
    public void fail() {
        throw new RuntimeException();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }


    @RequestMapping(value = "/tags")
    public List<TagEntity> tagList() {

        List<TagEntity> tagList = tagEntityRepository.findAll();
        return tagList;
    }

    @RequestMapping(value = "/tags/{tags}")
    public List<TagEntity> tagListAll(@PathVariable("tags") String tags) {

        if (tags != null) {
            List<TagEntity> tagListAll = tagEntityRepository.findAllByTagEquals(tags);
            return tagListAll;
        } else {
            return null;
        }
    }

    @RequestMapping(value="/moocs/{tags}", method = RequestMethod.GET)
    public List<MoocEntity> tagList(@PathVariable("tags") String tags) {


        if (tags != null) {

            List<String> jobTags = Arrays.asList(tags.split("\\s*,\\s*"));
            List<MoocEntity> moocList = new ArrayList<>();

            for (String matchOnjobTag : jobTags) {

                List<TagEntity> tagList = tagEntityRepository.findAllByTagEquals(matchOnjobTag);
                if (tagList != null) {


                    for (TagEntity tagEntity : tagList) {
                        MoocEntity moocEntity = moocEntityRepository.findByIdOrderByTitle(Integer.parseInt(tagEntity.getMoocid()));
                        moocList.add(moocEntity);
                    }
                    // model.addAttribute("moocs", moocList);
                }
            }

            return moocList;
        } else {
            return null;
        }
    }


    @RequestMapping("/moocs")
    public List<MoocEntity> moocList() {

        List<MoocEntity> moocList = moocEntityRepository.findAllByOrderByTitle();
        return moocList;
    }




}

