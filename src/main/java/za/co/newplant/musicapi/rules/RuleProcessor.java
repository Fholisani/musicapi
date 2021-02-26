package za.co.newplant.musicapi.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.newplant.musicapi.contstant.Status;
import za.co.newplant.musicapi.exception.error.Error;
import za.co.newplant.musicapi.exception.error.Errors;
import za.co.newplant.musicapi.model.Content;
import za.co.newplant.musicapi.model.DataContentResponse;

import java.io.File;

public  class RuleProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(RuleProcessor.class);
    public static Status statusRule(Content content){
        //TODO Decision for the music or content status - currently SAVED Uploaded
        return Status.UPLOADED;
    }

    public static Errors detailErrorStatusResponse(DataContentResponse dataContentResponse){
        if(dataContentResponse.getStatus() != null && dataContentResponse.getStatus().equalsIgnoreCase(Status.UPLOADED.name()) ){
            return errorObjectInit(0,"SUCCESS");
        }
        if(dataContentResponse.getStatus() != null && dataContentResponse.getStatus().equalsIgnoreCase(Status.REJECTED.name()) ){
            return errorObjectInit(1,"REJECTED");
        }
        if(dataContentResponse.getStatus() != null && dataContentResponse.getStatus().equalsIgnoreCase(Status.UPDATED.name()) ){
            return errorObjectInit(2,"UPDATED");
        }
        if(dataContentResponse.getStatus() != null && dataContentResponse.getStatus().equalsIgnoreCase(Status.REVIEW.name()) ){
            return errorObjectInit(3,"REVIEW");
        }
        return errorObjectInit(4,"Internal server error");
    }

    public static Errors errorObjectInit(Integer code, String message){
        Errors errors = new Errors();
        errors.add(new Error(code, message));
        return errors;
    }

    public static void deleteFiles(String pathName){
        File file = new File(pathName);
        if(file.delete()){
            LOG.info(pathName + " File deleted");
        }else LOG.info("File " + pathName + " doesn't exist");
    }

}
