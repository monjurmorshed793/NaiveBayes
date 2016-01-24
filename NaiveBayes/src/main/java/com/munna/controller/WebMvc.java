package com.munna.controller;

import com.munna.model.GenuineData;
import com.munna.model.NormalizedData;
import com.munna.services.NaiveBayes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by monju on 1/25/2016.
 */
@Controller
public class WebMvc {

    private NaiveBayes naiveBayes;

    @Autowired
    public void setNaiveBayes(NaiveBayes naiveBayes) {
        this.naiveBayes = naiveBayes;
    }

    @RequestMapping(value="/input", method= RequestMethod.POST)
    public String store(GenuineData genuineData){

        naiveBayes.saveGenuineData(genuineData);
        NormalizedData normalizedData = naiveBayes.generalizeData(genuineData);
        naiveBayes.saveNormalizedData(normalizedData);

        return "input";
    }

    @RequestMapping("/submit")
    public String submit(){

        return "submit";
    }

    @RequestMapping(value="/predict", method=RequestMethod.GET)
    public String predict( Model model){
        model.addAttribute("genuineData",new GenuineData());
        return "predict";
    }

    @RequestMapping(value="/result")
    public String result(GenuineData genuineData, Model model){
        NormalizedData normalizedData = naiveBayes.generalizeData(genuineData);

        String laplacian = naiveBayes.naiveBayesLaplacian(normalizedData);
        String priority = naiveBayes.naiveBayesPriority(normalizedData);

        model.addAttribute("laplacian",laplacian);
        model.addAttribute("priority",priority);

        return "result";
    }
}
