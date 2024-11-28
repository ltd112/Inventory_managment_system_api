package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException  extends RuntimeException {

    private String resourceName;
    private String fieldName;

    public BadRequestException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {

        return fieldName;
    }

    private static Map<String, Object> getParameters(String resourceName, String fieldName){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("resourceName", resourceName);
        parameters.put("fieldName", fieldName);
        return parameters;
    }
}
