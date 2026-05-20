package com.example.otademo.feature.home.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class FakeApkUpdateInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!request.url.encodedPath.endsWith("/api/releases/latest")) {
            return chain.proceed(request)
        }

        val responseBody = """
            {
              "latest_version_code": 2,
              "latest_version_name": "1.1.0",
              "min_supported_version_code": 1,
              "apk_url": "https://example.com/releases/ota-demo-1.1.0.apk",
              "release_notes": "Improve kiosk stability and add update readiness checks.",
              "checksum_sha256": "fake-checksum-for-development",
              "published_at": "2026-05-19T00:00:00Z"
            }
        """.trimIndent()

        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("OK")
            .body(responseBody.toResponseBody("application/json".toMediaType()))
            .build()
    }
}
