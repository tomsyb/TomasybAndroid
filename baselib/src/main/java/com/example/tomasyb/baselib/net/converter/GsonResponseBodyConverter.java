/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tomasyb.baselib.net.converter;

import com.example.tomasyb.baselib.net.entity.BaseResponse;
import com.example.tomasyb.baselib.net.exception.NoDataExceptionException;
import com.example.tomasyb.baselib.net.exception.ServerResponseException;
import com.google.gson.TypeAdapter;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        try {
            BaseResponse response = (BaseResponse) adapter.fromJson(value.charStream());
            if (response.getCode()!=0) {
                // 特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
                throw new ServerResponseException(response.getCode(), response.getMessage());
            } else if (response.getCode()==0) {
                if(response.getData()!=null)
                return response.getData();
                else throw new NoDataExceptionException();
            }
        } finally {
            value.close();
        }
        return null;
    }
}
