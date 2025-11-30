package com.example.angelastore;

public class ApiEnvelope<T> {
    private String type;
    private int code;
    private String message;
    private String status;
    private T data; //Ele poderia ser o tipo UserRegisterData ou UserLoginData, mas como o ApiEnvelope é pra ja encaixar
    // com as duas funções, o T é o coringa -- Cartada de mestre aqui filhote. Vamo tarcisooo.

    public String getType(){
        return type;
    }
    public int getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
    public String getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
}
