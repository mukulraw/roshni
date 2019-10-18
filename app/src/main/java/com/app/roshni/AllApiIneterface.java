package com.app.roshni;

import com.app.roshni.allWorkContrJobListPOJO.allWorkContrJobBean;
import com.app.roshni.contWorkerPOJO.contWorkerBeam;
import com.app.roshni.contractorJobDetailsPOJO.contractorJobDetailsBean;
import com.app.roshni.contractorPOJO.contractorBean;
import com.app.roshni.notificationBean.notificationBean;
import com.app.roshni.samplePOJO.sampleBean;
import com.app.roshni.sectorPOJO.sectorBean;
import com.app.roshni.verifyPOJO.verifyBean;
import com.app.roshni.workerJobListPOJO.workerJobDetailBean;
import com.app.roshni.workerJobListPOJO.workerJobListBean;
import com.app.roshni.workerListPOJO.workerListBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiIneterface {

    @Multipart
    @POST("roshni/api/login.php")
    Call<verifyBean> login(
            @Part("phone") String client,
            @Part("token") String token
    );

    @Multipart
    @POST("roshni/api/register_worker.php")
    Call<verifyBean> worker_signup(
            @Part("phone") String client,
            @Part("type") String type,
            @Part("token") String token
    );

    @Multipart
    @POST("roshni/api/verify.php")
    Call<verifyBean> verify(
            @Part("phone") String client,
            @Part("otp") String otp
    );

    @Multipart
    @POST("roshni/api/resend.php")
    Call<verifyBean> resend(
            @Part("phone") String client
    );

    @Multipart
    @POST("roshni/api/createPIN.php")
    Call<verifyBean> createPIN(
            @Part("user_id") String user_id,
            @Part("pin") String pin
    );

    @Multipart
    @POST("roshni/api/verifyPIN.php")
    Call<verifyBean> verifyPIN(
            @Part("user_id") String user_id,
            @Part("pin") String pin
    );

    @Multipart
    @POST("roshni/api/update_worker_personal.php")
    Call<verifyBean> updateWorkerPersonal(
            @Part("user_id") String user_id,
            @Part("name") String name,
            @Part("dob") String dob,
            @Part("gender") String gender,
            @Part("cpin") String cpin,
            @Part("cstate") String cstate,
            @Part("cdistrict") String cdistrict,
            @Part("carea") String carea,
            @Part("cstreet") String cstreet,
            @Part("ppin") String ppin,
            @Part("pstate") String pstate,
            @Part("pdistrict") String pdistrict,
            @Part("parea") String parea,
            @Part("pstreet") String pstreet,
            @Part("category") String category,
            @Part("religion") String religion,
            @Part("educational") String educational,
            @Part("marital") String marital,
            @Part("children") String children,
            @Part("belowsix") String belowsix,
            @Part("sixtofourteen") String sixtofourteen,
            @Part("fifteentoeighteen") String fifteentoeighteen,
            @Part("goingtoschool") String goingtoschool,
            @Part MultipartBody.Part file1
    );

    @Multipart
    @POST("roshni/api/update_brand.php")
    Call<verifyBean> updateBrand(
            @Part("user_id") String user_id,
            @Part("name") String name,
            @Part("registration_number") String registration_number,
            @Part("contact_person") String contact_person,
            @Part("cpin") String cpin,
            @Part("cstate") String cstate,
            @Part("cdistrict") String cdistrict,
            @Part("carea") String carea,
            @Part("cstreet") String cstreet,
            @Part("ppin") String ppin,
            @Part("pstate") String pstate,
            @Part("pdistrict") String pdistrict,
            @Part("parea") String parea,
            @Part("pstreet") String pstreet,
            @Part("manufacturing_units") String manufacturing_units,
            @Part("factory_outlet") String factory_outlet,
            @Part("products") String products,
            @Part("country") String country,
            @Part("workers") String workers,
            @Part("certification") String certification,
            @Part("expiry") String expiry,
            @Part("website") String website,
            @Part("email") String email,
            @Part MultipartBody.Part file1
    );


    @Multipart
    @POST("roshni/api/update_contractor.php")
    Call<verifyBean> update_contractor(
            @Part("user_id") String user_id,
            @Part("name") String name,
            @Part("dob") String dob,
            @Part("gender") String gender,
            @Part("business_name") String business_name,
            @Part("establishment_year") String establishment_year,
            @Part("cpin") String cpin,
            @Part("cstate") String cstate,
            @Part("cdistrict") String cdistrict,
            @Part("carea") String carea,
            @Part("cstreet") String cstreet,
            @Part("ppin") String ppin,
            @Part("pstate") String pstate,
            @Part("pdistrict") String pdistrict,
            @Part("parea") String parea,
            @Part("pstreet") String pstreet,
            @Part("home_units") String home_units,
            @Part("home_location") String home_location,
            @Part("workers_male") String workers_male,
            @Part("workers_female") String workers_female,
            @Part("experience") String experience,
            @Part("work_type") String work_type,
            @Part("availability") String availability,
            @Part("employer") String employer,
            @Part("about") String about,
            @Part MultipartBody.Part file1
    );


    @Multipart
    @POST("roshni/api/post_job.php")
    Call<verifyBean> postjob(
            @Part("brand_id") String brand_id,
            @Part("title") String title,
            @Part("skills") String skills,
            @Part("preferred") String preferred,
            @Part("location") String location,
            @Part("experience") String experience,
            @Part("role") String role,
            @Part("gender") String gender,
            @Part("education") String education,
            @Part("hours") String hours,
            @Part("salary") String salary,
            @Part("stype") String stype
    );

    @Multipart
    @POST("roshni/api/update_job.php")
    Call<verifyBean> UpdateWorkerJob(
            @Part("id") String id,
            @Part("title") String title,
            @Part("skills") String skills,
            @Part("preferred") String preferred,
            @Part("location") String location,
            @Part("experience") String experience,
            @Part("role") String role,
            @Part("gender") String gender,
            @Part("education") String education,
            @Part("hours") String hours,
            @Part("salary") String salary,
            @Part("stype") String stype
    );

    @Multipart
    @POST("roshni/api/update_job2.php")
    Call<verifyBean> UpdateContractorJob(
            @Part("id") String id,
            @Part("job_type") String job_type,
            @Part("experience") String experience,
            @Part("days") String days,
            @Part("rate") String rate,
            @Part MultipartBody.Part file1
    );

    @Multipart
    @POST("roshni/api/post_job_contractor.php")
    Call<verifyBean> post_job_contractor(
            @Part("contractor_id") String contractor_id,
            @Part("job_type") String job_type,
            @Part("experience") String experience,
            @Part("days") String days,
            @Part("rate") String rate,
            @Part MultipartBody.Part file1
    );

    @Multipart
    @POST("roshni/api/apply_job.php")
    Call<verifyBean> apply_job(
            @Part("job_id") String job_id,
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/apply_job2.php")
    Call<verifyBean> apply_job2(
            @Part("job_id") String job_id,
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/worker_ac_inac.php")
    Call<verifyBean> worker_ac_inac(
            @Part("jid") String jid,
            @Part("status") String status
    );

    @Multipart
    @POST("roshni/api/contractor_ac_inac.php")
    Call<verifyBean> contractor_ac_inac(
            @Part("jid") String jid,
            @Part("status") String status
    );

    @Multipart
    @POST("roshni/api/worker_acept_reject.php")
    Call<verifyBean> worker_acept_reject(
            @Part("jid") String jid,
            @Part("id") String id,
            @Part("status") String status
    );

    @Multipart
    @POST("roshni/api/contractor_acept_reject.php")
    Call<verifyBean> contractor_acept_reject(
            @Part("jid") String jid,
            @Part("id") String id,
            @Part("status") String status
    );



    @Multipart
    @POST("roshni/api/update_worker_professional.php")
    Call<verifyBean> updateWorkerProfessional(
            @Part("user_id") String user_id,
            @Part("sector") String sector,
            @Part("skills") String skills,
            @Part("experience") String experience,
            @Part("employment") String employment,
            @Part("employer") String employer,
            @Part("home") String home,
            @Part("workers") String workers,
            @Part("looms") String looms,
            @Part("location") String location
    );

    @Multipart
    @POST("roshni/api/getJobListForWorker.php")
    Call<workerJobListBean> getJobListForWorker(
            @Part("user_id") String user_id,
            @Part("date") String date
    );

    @Multipart
    @POST("roshni/api/getJobListForContractor.php")
    Call<workerJobListBean> getJobListForContractor(
            @Part("user_id") String user_id,
            @Part("date") String date
    );


    @Multipart
    @POST("roshni/api/getAllWorkerJobs.php")
    Call<allWorkContrJobBean> getAllWorkerJobs(
            @Part("brand_id") String brand_id,
            @Part("status") String status,
            @Part("date") String date
    );

    @Multipart
    @POST("roshni/api/getAllContractorJobs.php")
    Call<allWorkContrJobBean> getAllContractorJobs(
            @Part("brand_id") String brand_id,
            @Part("status") String status,
            @Part("date") String date
    );


    @Multipart
    @POST("roshni/api/getAppliedListForWorker.php")
    Call<workerJobListBean> getAppliedListForWorker(
            @Part("user_id") String user_id,
            @Part("date") String date
    );

    @Multipart
    @POST("roshni/api/getAppliedListForContractor.php")
    Call<workerJobListBean> getAppliedListForContractor(
            @Part("user_id") String user_id,
            @Part("date") String date
    );

    @Multipart
    @POST("roshni/api/getJobDetailsForWorker.php")
    Call<workerJobDetailBean> getJobDetailForWorker(
            @Part("user_id") String user_id,
            @Part("jid") String jid
    );

    @Multipart
    @POST("roshni/api/getJobDetailsForContractor.php")
    Call<contractorJobDetailsBean> getJobDetailsForContractor(
            @Part("user_id") String user_id,
            @Part("jid") String jid
    );

    @GET("roshni/api/getSectors.php")
    Call<sectorBean> getSectors();

    @GET("roshni/api/getRoles.php")
    Call<sectorBean> getRoles();

    @GET("roshni/api/getSkills.php")
    Call<sectorBean> getSkills();

    @GET("roshni/api/getLocations.php")
    Call<sectorBean> getLocations();

    @GET("roshni/api/getAllWorkers.php")
    Call<workerListBean> getAllWorkers();

    @Multipart
    @POST("roshni/api/getContWorkers.php")
    Call<contWorkerBeam> getContWorkers(
            @Part("cuid") String cuid
    );

    @GET("roshni/api/getAllConttractors.php")
    Call<workerListBean> getAllConttractors();

    @Multipart
    @POST("roshni/api/getAppliedWorkers.php")
    Call<workerListBean> getAppliedWorkers(
            @Part("jid") String jid
    );

    @Multipart
    @POST("roshni/api/getAppliedContractors.php")
    Call<workerListBean> getAppliedContractors(
            @Part("jid") String jid
    );

    @Multipart
    @POST("roshni/api/getWorkerById.php")
    Call<workerListBean> getWorkerById(
            @Part("id") String id
    );

    @Multipart
    @POST("roshni/api/getBrandNoti.php")
    Call<notificationBean> getBrandNoti(
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/getContractorNoti.php")
    Call<notificationBean> getContractorNoti(
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/getWorkerNoti.php")
    Call<notificationBean> getWorkerNoti(
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/getSamples.php")
    Call<sampleBean> getSamples(
            @Part("user_id") String user_id
    );

    @Multipart
    @POST("roshni/api/uploadSample.php")
    Call<sampleBean> uploadSample(
            @Part("user_id") String user_id,
            @Part MultipartBody.Part file1
    );

    @Multipart
    @POST("roshni/api/deleteSample.php")
    Call<sampleBean> deleteSample(
            @Part("id") String id
    );

    @Multipart
    @POST("roshni/api/getContractorById.php")
    Call<contractorBean> getContractorById(
            @Part("id") String id
    );

}
