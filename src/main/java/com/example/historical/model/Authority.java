package com.example.historical.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "AUTHORITY")
public class Authority extends Identifiable{

    @Column(name = "NAME", length = 50)
//    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<Account> accounts;
    
    public Authority() {
        super(null, 1L);
    }
    
    public Authority(AuthorityName name) {
        super(null, 1L);
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}