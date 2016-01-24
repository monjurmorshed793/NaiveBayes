package com.munna.services;

import com.munna.model.GenuineData;
import com.munna.model.NormalizedData;

/**
 * Created by monju on 1/24/2016.
 */
public interface NaiveBayes {

    public void saveGenuineData(GenuineData genuineData);

    public NormalizedData generalizeData(GenuineData genuineData);

    public void saveNormalizedData(NormalizedData normalizedData);

    public String naiveBayesLaplacian(NormalizedData normalizedData);

    public String naiveBayesPriority(NormalizedData normalizedData);
}
