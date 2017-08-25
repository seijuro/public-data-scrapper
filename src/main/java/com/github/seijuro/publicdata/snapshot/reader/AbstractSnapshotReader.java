package com.github.seijuro.publicdata.snapshot.reader;

import com.github.seijuro.publicdata.snapshot.SnapshotRequest;
import com.github.seijuro.publicdata.snapshot.SnapshotResult;

import java.sql.Connection;
import java.util.Objects;

public interface AbstractSnapshotReader {
    public abstract SnapshotResult read(SnapshotRequest key);
}
