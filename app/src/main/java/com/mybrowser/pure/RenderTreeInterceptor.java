package com.mybrowser.pure;

import android.content.Context;

public class RenderTreeInterceptor {
    
    private Context context;

    public RenderTreeInterceptor(Context context) {
        this.context = context;
    }

    public String getInterceptorCode() {
        return "(function() {" +
                "    'use strict';" +
                "" +
                "    console.log('🚀 بدء التدخل في شجرة العرض');" +
                "" +
                "    /* البحث عن المحتوى المستهدف */" +
                "    function findContent() {" +
                "        let content = null;" +
                "        " +
                "        // البحث عن الفيديو" +
                "        const videos = document.querySelectorAll('video');" +
                "        for (let video of videos) {" +
                "            if (video.videoWidth > 320 && video.videoHeight > 240) {" +
                "                const className = (video.className || '').toLowerCase();" +
                "                const id = (video.id || '').toLowerCase();" +
                "                " +
                "                if (!className.includes('ad') && !id.includes('ad')) {" +
                "                    content = video;" +
                "                    break;" +
                "                }" +
                "            }" +
                "        }" +
                "        " +
                "        // إذا لم يوجد فيديو، ابحث عن صورة" +
                "        if (!content) {" +
                "            const images = document.querySelectorAll('img');" +
                "            for (let img of images) {" +
                "                if (img.naturalWidth >= 600 && img.naturalHeight >= 400) {" +
                "                    content = img;" +
                "                    break;" +
                "                }" +
                "            }" +
                "        }" +
                "        " +
                "        return content;" +
                "    }" +
                "    " +
                "    /* عرض المحتوى فقط */" +
                "    setTimeout(() => {" +
                "        const content = findContent();" +
                "        " +
                "        if (content) {" +
                "            console.log('✅ تم العثور على محتوى');" +
                "            " +
                "            // إنشاء تنسيق خاص للمحتوى" +
                "            const style = document.createElement('style');" +
                "            style.textContent = `" +
                "                body, body * {" +
                "                    display: none !important;" +
                "                }" +
                "                " +
                "                .pure-content {" +
                "                    display: block !important;" +
                "                    position: fixed !important;" +
                "                    top: 0 !important;" +
                "                    left: 0 !important;" +
                "                    width: 100vw !important;" +
                "                    height: 100vh !important;" +
                "                    object-fit: contain !important;" +
                "                    z-index: 999999 !important;" +
                "                    background: #000 !important;" +
                "                }" +
                "            `;" +
                "            " +
                "            document.head.appendChild(style);" +
                "            content.classList.add('pure-content');" +
                "            " +
                "            // تفعيل التحكم للفيديو" +
                "            if (content.tagName === 'VIDEO') {" +
                "                content.controls = true;" +
                "            }" +
                "        } else {" +
                "            console.warn('⚠️ لم يتم العثور على محتوى');" +
                "        }" +
                "    }, 1000);" +
                "" +
                "})();";
    }
}
