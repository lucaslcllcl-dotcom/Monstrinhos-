package com.caeless.monstrinhos;

/**
 * Offline-only placeholders for future monetization UI.
 * No SDK, network request, billing flow or ad request is performed here.
 */
public final class MonetizationMockCatalog {
    public static final boolean REAL_ADS_ENABLED = false;
    public static final boolean REAL_PURCHASES_ENABLED = false;

    private MonetizationMockCatalog() {}

    public static final class Offer {
        public final String id;
        public final String title;
        public final String description;
        public final String displayPrice;
        public final boolean enabled;

        private Offer(String id, String title, String description, String displayPrice) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.displayPrice = displayPrice;
            this.enabled = false;
        }

        public String accessibilityLabel() {
            return title + ". " + description + ". Indisponível nesta versão.";
        }
    }

    private static final Offer[] STORE = {
        new Offer("coins_small", "Saquinho de moedas", "Pacote demonstrativo de moedas", "Em breve"),
        new Offer("coins_medium", "Baú de moedas", "Pacote demonstrativo de moedas", "Em breve"),
        new Offer("remove_ads", "Remover anúncios", "Opção demonstrativa para uma etapa futura", "Em breve")
    };

    public static Offer[] storeSnapshot() {
        return STORE.clone();
    }

    public static String adPlaceholderLabel() {
        return "Espaço de anúncio desativado nesta versão";
    }

    public static boolean canRequestAd() {
        return false;
    }

    public static boolean canStartPurchase() {
        return false;
    }
}
