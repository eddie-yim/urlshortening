package com.example.presentation.url;

import com.example.application.url.UrlApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Controller
public class UrlController {

    private final UrlApplicationService urlService;

    @Value("${base-url}")
    private String baseUrl;

    public UrlController(UrlApplicationService urlConversionService) {
        this.urlService = urlConversionService;
    }

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        model.addAttribute("urlRequest", new UrlRequest(null));
        return "index";
    }

    @GetMapping("/{shortened:[0-9A-Za-z]{1,8}}")
    public String redirect(@PathVariable String shortened) {
        log.debug("SHORTENED: {}", shortened);
        if (!this.urlService.shortenedUrlExists(shortened)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String sourceUrl = this.urlService.toOriginal(shortened);
        return "redirect:" + sourceUrl;
    }

    @PostMapping("/shorten")
    public String shorten(UrlRequest urlRequest, BindingResult result, Model model) {
        UrlRequestValidator validator = new UrlRequestValidator();
        validator.validate(urlRequest, result);
        if (result.hasErrors()) {
            model.addAttribute("urlRequest", urlRequest);
            return "index";
        }

        try {
            Pair<String, String> finalizedAndShortened = this.urlService.shorten(urlRequest.getOriginal().trim());
            String shortened = String.format(
                "%s/%s",
                this.baseUrl,
                finalizedAndShortened.getSecond()
            );
            model.addAttribute("original", finalizedAndShortened.getFirst());
            model.addAttribute("shortened", shortened);
            return "shortened";
        } catch (Exception e) {
            result.rejectValue("original", "url.original.invalid");
            model.addAttribute("urlRequest", urlRequest);
            return "index";
        }
    }
}
