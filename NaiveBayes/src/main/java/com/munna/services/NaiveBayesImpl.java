package com.munna.services;

import com.munna.configuration.HibernateUtil;
import com.munna.model.GenuineData;
import com.munna.model.NormalizedData;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by monju on 1/24/2016.
 */

@Service
public class NaiveBayesImpl implements NaiveBayes {



    @Override
    public void saveGenuineData(GenuineData genuineData) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(genuineData);
        transaction.commit();
        session.close();
    }

    @Override
    public NormalizedData generalizeData(GenuineData genuineData) {

        NormalizedData normalizedData = new NormalizedData();

        int age = genuineData.getAge();

        if(age<20){
            normalizedData.setAge("young");
        }
        else if(age>=21 && age <=40){
            normalizedData.setAge("middle");
        }
        else{
            normalizedData.setAge("old");
        }

        normalizedData.setGender(genuineData.getGender().toLowerCase());

        double db = genuineData.getDb();

        if(db<0){
            normalizedData.setDb("db_low");
        }
        else if(db>=0 && db<=0.3){
            normalizedData.setDb("db_normal");
        }
        else{
            normalizedData.setDb("db_high");
        }

        double tb = genuineData.getTb();

        if(tb<0.3){
            normalizedData.setTb("tb_low");
        }
        else if(tb>=0.3 && tb<=1.9){
            normalizedData.setTb("tb_normal");
        }
        else{
            normalizedData.setTb("tb_high");
        }

        double tp = genuineData.getTp();

        if(tp<0.6){
            normalizedData.setTp("tp_low");
        }
        else if(tp>=0.6 && tp<=8.3){
            normalizedData.setTp("tp_normal");
        }
        else{
            normalizedData.setTp("tp_high");
        }

        double alb = genuineData.getAlb();

        if(alb<0.35){
            normalizedData.setAlb("alb_low");
        }
        else if(alb>=0.35 && alb<=0.6){
            normalizedData.setAlb("alb_normal");
        }
        else{
            normalizedData.setAlb("alb_high");
        }

        double alkphos = genuineData.getAlkphos();

        if(alkphos<25){
            normalizedData.setAlkphos("alkphos_low");
        }
        else if(alkphos>=25 && alkphos<=100){
            normalizedData.setAlkphos("akphos_normal");
        }
        else{
            normalizedData.setAlkphos("alkphos_high");
        }

        double sgpt = genuineData.getSgpt();

        if(sgpt<10){
            normalizedData.setSgpt("sgpt_low");
        }
        else if(sgpt>=10 && sgpt<=40){
            normalizedData.setSgpt("sgpt_normal");
        }
        else{
            normalizedData.setSgpt("sgpt_high");
        }

        double sgot = genuineData.getSgot();

        if(sgot<14){
            normalizedData.setSgot("sgot_low");
        }
        else if(sgot>=14 && sgot<=20){
            normalizedData.setSgot("sgot_normal");
        }
        else{
            normalizedData.setSgot("sgot_high");
        }

        double ag = genuineData.getAg();

        if(ag<0.72){
            normalizedData.setAg("ag_low");
        }
        else if(ag>=0.72 && ag<=0.8){
            normalizedData.setAg("ag_normal");
        }
        else{
            normalizedData.setAg("ag_high");
        }

        if(!genuineData.getDisease().isEmpty()){
        String disease = genuineData.getDisease();


            if (disease.equals("1")) {
                normalizedData.setDisease("no");
            } else {
                normalizedData.setDisease("yes");
            }
        }
        else{
            normalizedData.setDisease("no");
        }

        return normalizedData;
    }

    @Override
    public void saveNormalizedData(NormalizedData normalizedData) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(normalizedData);
        transaction.commit();
        session.close();
    }

    @Override
    public String naiveBayesLaplacian(NormalizedData n) {

        String[] attributes = {"age","gender","tb","db","alkphos","sgpt","sgot","tp","alb","ag"};
        String[] attributeValues={n.getAge(),n.getGender(),n.getTb(),n.getDb(),n.getAlkphos(),n.getSgpt(),n.getSgot(),n.getTp(),n.getAlb(),n.getAg()};

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(NormalizedData.class);

        Criteria criteria1 = session.createCriteria(NormalizedData.class);
        Criteria criteria2 = session.createCriteria(NormalizedData.class);

        Criterion criterionYes = Restrictions.eq("disease","yes");
        Criterion criterionNo = Restrictions.eq("disease","no");

        List<NormalizedData> totalData = criteria.list();
        int totalSize = totalData.size();

        List<NormalizedData> totalYesData = criteria1.add(criterionYes).list();
        int totalYes = totalYesData.size();


        List<NormalizedData> totalNoData = criteria2.add(criterionNo).list();
        int totalNo = totalNoData.size();


        double probabilityOfYes = (double)((totalYes / totalSize)+ attributes.length);

        System.out.println("Total Probability of Yes: "+probabilityOfYes);


        double probabilityOfNo = (double)(totalNo/ totalSize)+ attributes.length;
        System.out.println("Total probability of No : "+ probabilityOfNo);

        double totalProbabilityYes = probabilityOfYes;
        double totalProbabilityNo = probabilityOfNo;

        for(int i=0; i< attributes.length; i++){
            Criteria criteriaYesFinal = session.createCriteria(NormalizedData.class);
            Criteria criteriaNoFinal = session.createCriteria(NormalizedData.class);

            Criterion criterionA = Restrictions.eq("disease","yes");
            Criterion criterionB = Restrictions.eq("disease","no");

            Criterion criterionY = Restrictions.eq(attributes[i],attributeValues[i]);
            Criterion criterionN = Restrictions.eq(attributes[i],attributeValues[i]);

            List<NormalizedData> aa = criteriaYesFinal.add(criterionA).add(criterionY).list();
            List<NormalizedData> bb = criteriaNoFinal.add(criterionB).add(criterionY).list();

            totalProbabilityYes*=(double)((aa.size()+1)/totalYes);
            totalProbabilityNo*=(double)((bb.size()+1)/totalNo);

        }

        session.close();

        if(totalProbabilityYes> totalProbabilityNo){
            return "yes";
        }
        else{
            return "no";
        }

    }

    @Override
    public String naiveBayesPriority(NormalizedData n) {

        String[] attributes = {"age","gender","tb","db","alkphos","sgpt","sgot","tp","alb","ag"};
        String[] attributeValues={n.getAge(),n.getGender(),n.getTb(),n.getDb(),n.getAlkphos(),n.getSgpt(),n.getSgot(),n.getTp(),n.getAlb(),n.getAg()};

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(NormalizedData.class);

        Criteria criteria1 = session.createCriteria(NormalizedData.class);
        Criteria criteria2 = session.createCriteria(NormalizedData.class);

        Criterion criterionYes = Restrictions.eq("disease","yes");
        Criterion criterionNo = Restrictions.eq("disease","no");

        List<NormalizedData> totalData = criteria.list();
        int totalSize = totalData.size();

        List<NormalizedData> totalYesData = criteria1.add(criterionYes).list();
        int totalYes = totalYesData.size();


        List<NormalizedData> totalNoData = criteria2.add(criterionNo).list();
        int totalNo = totalNoData.size();


        double probabilityOfYes = (double)((totalYes / totalSize)+ attributes.length);

        System.out.println("Total Probability of Yes: "+probabilityOfYes);


        double probabilityOfNo = (double)(totalNo/ totalSize)+ attributes.length;
        System.out.println("Total probability of No : "+ probabilityOfNo);

        double totalProbabilityYes = probabilityOfYes;
        double totalProbabilityNo = probabilityOfNo;

        int countYes = 0;
        int countNo = 0;

        for(int i=0;i< attributes.length; i++){

            Criteria criteriaYesFinal = session.createCriteria(NormalizedData.class);
            Criteria criteriaNoFinal = session.createCriteria(NormalizedData.class);

            Criterion criterionA = Restrictions.eq("disease","yes");
            Criterion criterionB = Restrictions.eq("disease","no");

            Criterion criterionY = Restrictions.eq(attributes[i],attributeValues[i]);
            Criterion criterionN = Restrictions.eq(attributes[i],attributeValues[i]);

            List<NormalizedData> aa = criteriaYesFinal.add(criterionA).add(criterionY).list();
            List<NormalizedData> bb = criteriaNoFinal.add(criterionB).add(criterionY).list();

            double totalProbabilityY=(double)((aa.size())/totalYes);
            double totalProbabilityN=(double)((bb.size())/totalNo);

            if(totalProbabilityY>totalProbabilityN)
                countYes+=1;
            else
                countNo+=1;

        }

        if((totalProbabilityYes* countYes)>(totalProbabilityNo * countNo)){
            return "yes";
        }
        else{
            return "no";
        }

    }
}
