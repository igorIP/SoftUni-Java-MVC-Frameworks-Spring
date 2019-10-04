package exodia.web.controllers;

import java.util.Optional;
import java.util.UUID;

class BaseController {

    protected BaseController() {
    }

    protected static Optional<UUID> uuid(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    protected static String redirect(String url) {
        return "redirect:" + url;
    }
}
