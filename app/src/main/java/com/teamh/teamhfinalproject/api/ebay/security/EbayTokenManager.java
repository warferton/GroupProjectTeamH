package com.teamh.teamhfinalproject.api.ebay.security;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EbayTokenManager extends OAuth2Api {
    private static final List<String> scopes = Arrays.asList(
            "https://api.ebay.com/oauth/api_scope",
            "https://api.ebay.com/oauth/api_scope/buy.order.readonly",
            "https://api.ebay.com/oauth/api_scope/buy.guest.order",
            "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.marketing",
            "https://api.ebay.com/oauth/api_scope/sell.inventory.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.inventory",
            "https://api.ebay.com/oauth/api_scope/sell.account.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.account",
            "https://api.ebay.com/oauth/api_scope/sell.fulfillment.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.fulfillment",
            "https://api.ebay.com/oauth/api_scope/sell.analytics.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.marketplace.insights.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.catalog.readonly",
            "https://api.ebay.com/oauth/api_scope/buy.shopping.cart",
            "https://api.ebay.com/oauth/api_scope/buy.offer.auction",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.email.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.phone.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.address.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.name.readonly",
            "https://api.ebay.com/oauth/api_scope/commerce.identity.status.readonly",
            "https://api.ebay.com/oauth/api_scope/sell.finances",
            "https://api.ebay.com/oauth/api_scope/sell.item.draft",
            "https://api.ebay.com/oauth/api_scope/sell.payment.dispute",
            "https://api.ebay.com/oauth/api_scope/sell.item",
            "https://api.ebay.com/oauth/api_scope/sell.reputation",
            "https://api.ebay.com/oauth/api_scope/sell.reputation.readonly"

    );


//TODO =======================
    /**
     * Should get the Authorization URL to redirect the user to.
     * Once the user authenticates and approves the consent,
     * the callback need to be captured by the redirect URL setup by the app
     * and then call OAuth2Api.exchangeCodeForAccessToken()
     * to get the refresh and access tokens.
     */
    public void getRefreshAndAccessTokens() {
        String uri = this.generateUserAuthorizationUrl(
                Environment.SANDBOX,
                scopes,
                Optional.of("")// IDK what should be here
        );
//        System.out.println(uri);

        String code  = ""; // = redirectTo() : String --- PLACEHOLDER
        try {
            this.exchangeCodeForAccessToken(Environment.SANDBOX, code);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

// some keys
//    RuName(Redirect) : Ivan_Khozhaynov-IvanKhoz-Gifter-tyiyiy
//    SignIn OAuth URI : https://auth.sandbox.ebay.com/oauth2/authorize?client_id=IvanKhoz-Gifter-SBX-319baf4db-cecf3f71&response_type=code&redirect_uri=Ivan_Khozhaynov-IvanKhoz-Gifter-tyiyiy&scope=https://api.ebay.com/oauth/api_scope https://api.ebay.com/oauth/api_scope/buy.order.readonly https://api.ebay.com/oauth/api_scope/buy.guest.order https://api.ebay.com/oauth/api_scope/sell.marketing.readonly https://api.ebay.com/oauth/api_scope/sell.marketing https://api.ebay.com/oauth/api_scope/sell.inventory.readonly https://api.ebay.com/oauth/api_scope/sell.inventory https://api.ebay.com/oauth/api_scope/sell.account.readonly https://api.ebay.com/oauth/api_scope/sell.account https://api.ebay.com/oauth/api_scope/sell.fulfillment.readonly https://api.ebay.com/oauth/api_scope/sell.fulfillment https://api.ebay.com/oauth/api_scope/sell.analytics.readonly https://api.ebay.com/oauth/api_scope/sell.marketplace.insights.readonly https://api.ebay.com/oauth/api_scope/commerce.catalog.readonly https://api.ebay.com/oauth/api_scope/buy.shopping.cart https://api.ebay.com/oauth/api_scope/buy.offer.auction https://api.ebay.com/oauth/api_scope/commerce.identity.readonly https://api.ebay.com/oauth/api_scope/commerce.identity.email.readonly https://api.ebay.com/oauth/api_scope/commerce.identity.phone.readonly https://api.ebay.com/oauth/api_scope/commerce.identity.address.readonly https://api.ebay.com/oauth/api_scope/commerce.identity.name.readonly https://api.ebay.com/oauth/api_scope/commerce.identity.status.readonly https://api.ebay.com/oauth/api_scope/sell.finances https://api.ebay.com/oauth/api_scope/sell.item.draft https://api.ebay.com/oauth/api_scope/sell.payment.dispute https://api.ebay.com/oauth/api_scope/sell.item https://api.ebay.com/oauth/api_scope/sell.reputation https://api.ebay.com/oauth/api_scope/sell.reputation.readonly
//    ClientID : IvanKhoz-Gifter-SBX-319baf4db-cecf3f71
//    ClientSecret : SBX-19baf4db793e-3764-4051-987f-a0d6


}
