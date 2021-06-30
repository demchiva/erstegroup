package cz.erstegroup.example.demo.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
final public class TransparentAccount {
    private String accountNumber;
    private String bankCode;
    private String transparencyFrom;
    private String transparencyTo;
    private String publicationTo;
    private String actualizationDate;
    private String balance;
    private String currency;
    private String name;
    private String iban;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getTransparencyFrom() {
        return transparencyFrom;
    }

    public void setTransparencyFrom(String transparencyFrom) {
        this.transparencyFrom = transparencyFrom;
    }

    public String getTransparencyTo() {
        return transparencyTo;
    }

    public void setTransparencyTo(String transparencyTo) {
        this.transparencyTo = transparencyTo;
    }

    public String getPublicationTo() {
        return publicationTo;
    }

    public void setPublicationTo(String publicationTo) {
        this.publicationTo = publicationTo;
    }

    public String getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(String actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "TransparentAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", transparencyFrom='" + transparencyFrom + '\'' +
                ", transparencyTo='" + transparencyTo + '\'' +
                ", publicationTo='" + publicationTo + '\'' +
                ", actualizationDate='" + actualizationDate + '\'' +
                ", balance='" + balance + '\'' +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
