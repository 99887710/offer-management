package idv.jeff.offer.mgmt.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private String msg;

    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException( String resourceName, String msg, String fieldName, Object fieldValue) {
        super(String.format("%s %s with %s : '%s'", resourceName, msg, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.msg = msg;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getMsg() {return msg;}

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}

