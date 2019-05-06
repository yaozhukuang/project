package com.zw.yzk.component.network.observer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;

import com.zw.yzk.component.network.bean.DEntity;
import com.zw.yzk.component.network.bean.DTask;
import com.zw.yzk.component.network.exception.CustomException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.ResponseBody;

/**
 * 下载
 */
public abstract class AbstractDownloadObserver extends DefaultObserver<ResponseBody> {

    private Context context;
    private DTask task;

    public AbstractDownloadObserver(Context context, DTask task) {
        this.context = context;
        this.task = task;
    }

    /**
     * 下载进度回调
     */
    public abstract void onProgress(DEntity entity);

    @Override
    public void onNext(ResponseBody body) {
        new SaveTask().execute(body);
    }

    /**
     * 获取保存文件名
     */
    private File getFilePath(DTask task) {
        if (TextUtils.isEmpty(task.url)) {
            throw new CustomException("download url can not be null");
        }
        File filePath;
        String fileName;
        if (TextUtils.isEmpty(task.fileName)) {
            fileName = task.url.substring(task.url.lastIndexOf("/") + 1);
        } else {
            fileName = task.fileName;
        }
        if (TextUtils.isEmpty(task.path)) {
            filePath = getDiskCacheDir(context, fileName);
        } else {
            File parent = new File(task.path);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            filePath = new File(task.path, fileName);
            if (filePath.exists()) {
                filePath.delete();
            }
        }
        return filePath;
    }

    /**
     * 获取文件存储路径
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null) {
                cachePath = context.getExternalCacheDir().getPath();
            } else {
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private class SaveTask extends AsyncTask<ResponseBody, DEntity, Throwable> {

        @Override
        protected Throwable doInBackground(ResponseBody... body) {
            //总文件长度
            long fileLength = body[0].contentLength();
            //文件保存路径
            File filePath = getFilePath(task);

            DEntity entity = new DEntity();
            entity.filePath = filePath.getAbsolutePath();
            entity.fileLength = fileLength;

            try {
                //创建写入流
                InputStream inputStream = body[0].byteStream();
                RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
                byte[] buf = new byte[4096];
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    //写入文件
                    rf.write(buf, 0, len);
                    //文件已读长度
                    entity.readLength += len;
                    //回调
                    publishProgress(entity);
                }
                rf.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                return e;
            } catch (IOException e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(DEntity... values) {
            super.onProgressUpdate(values);
            onProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Throwable throwable) {
            super.onPostExecute(throwable);
            if (throwable != null) {
                onError(throwable);
            }
        }
    }

}
