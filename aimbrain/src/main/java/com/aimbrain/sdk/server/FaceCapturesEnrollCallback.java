package com.aimbrain.sdk.server;

import android.util.Base64;

import com.aimbrain.sdk.models.FaceEnrollModel;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class FaceCapturesEnrollCallback implements FaceCapturesCallback {

    public abstract void success(FaceEnrollModel response);

    public abstract void failure(VolleyError error);

    @Override
    public void fireSuccessAction(JSONObject response) {
        FaceEnrollModel faceEnrollModel = null;
        try {
            int imagesCount = response.getInt("imagesCount");
            byte[] metadata = null;
            if (response.has("metadata")) {
                String metadataString = response.getString("metadata");
                metadata = Base64.decode(metadataString, Base64.DEFAULT);
            }
            faceEnrollModel = new FaceEnrollModel(imagesCount, metadata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        success(faceEnrollModel);
    }

}
