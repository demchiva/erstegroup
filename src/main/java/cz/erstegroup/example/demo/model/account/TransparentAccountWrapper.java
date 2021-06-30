package cz.erstegroup.example.demo.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransparentAccountWrapper {
    private List<TransparentAccount> accounts;

    public List<TransparentAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<TransparentAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "TransparentAccountWrapper{" +
                "accounts=" + accounts +
                '}';
    }
}
