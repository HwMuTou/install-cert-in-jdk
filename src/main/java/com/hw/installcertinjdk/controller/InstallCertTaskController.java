package com.hw.installcertinjdk.controller;

import com.hw.installcertinjdk.service.InstallCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@RestController(value = "/cert")
public class InstallCertTaskController {

    private final InstallCertService installCertService;

    @Autowired
    public InstallCertTaskController(InstallCertService installCertService) {
        this.installCertService = installCertService;
    }

    @PostMapping
    public Mono<String> create(@RequestPart String url,
                               @RequestPart String password) {
        return Mono.from(subscriber -> {
            try {
                installCertService.installCert(url, password);
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException | KeyManagementException e) {
                e.printStackTrace();
            }
        });
    }
}
