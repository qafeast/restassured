package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Maps the single user API response and ignores any fields we do not model
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private UserData data;
    private Support support;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

}
