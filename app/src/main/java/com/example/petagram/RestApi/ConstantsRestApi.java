package com.example.petagram.RestApi;

public final class ConstantsRestApi {

    public static final String VERSION = "/v9.0/";
    public static final String ROOT_URL = "https://graph.facebook.com" + VERSION;
    public static final String ACCESS_TOKEN = "EAACXe5ZBlrkEBAFbwEL0aZAna7spj5DVb4J8js2ab22ZAsg1pMqtVX7sYG51hJ3Ag8gUa8KPt8B2ZAuRbDFZBvkDnYcV5OHAnIE1DF4rw4gM84TZB4JnZBUsRvauoTgtK5VBbctlaqv0kIW33aclyNZAxxeS8q1L0m9XimleqbqCmgZDZD";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String USER_ID = "17841445182555913";
    public static final String KEY_USER_MEDIA = "/media?fields=id,caption,media_type,media_url,owner,username,like_count";
    public static final String URL_USER_MEDIA = USER_ID + KEY_USER_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://graph.facebook.com/v9.0/17841445182555913/media?fields=id,media_type,media_url,like_count,owner,timestamp&access_token=EAACXe5ZBlrkEBAFbwEL0aZAna7spj5DVb4J8js2ab22ZAsg1pMqtVX7sYG51hJ3Ag8gUa8KPt8B2ZAuRbDFZBvkDnYcV5OHAnIE1DF4rw4gM84TZB4JnZBUsRvauoTgtK5VBbctlaqv0kIW33aclyNZAxxeS8q1L0m9XimleqbqCmgZDZD
    public static final String KEY_BIO_URL = "?fields=biography,followers_count,follows_count,name,media_count,username,profile_picture_url";
    public static final String URL_USER_BIO = USER_ID + KEY_BIO_URL+ KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
