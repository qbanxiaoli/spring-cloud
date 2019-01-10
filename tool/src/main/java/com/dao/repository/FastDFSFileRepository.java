package com.dao.repository;

import com.model.entity.FastDFSFile;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:59 PM
 */
public interface FastDFSFileRepository extends BaseRepository<FastDFSFile, Long> {

    FastDFSFile findByStorePath(String fileUrl);

}
