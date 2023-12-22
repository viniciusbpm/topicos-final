package br.ifsul.topicosfinal.helpers.api;

public interface CallbackData<T> {

    void onSuccess(T data);

    void onUnsuccess(String message);

    void onFailure(String message);

}
