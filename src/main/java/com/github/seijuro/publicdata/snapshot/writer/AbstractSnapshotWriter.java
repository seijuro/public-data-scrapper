package com.github.seijuro.publicdata.snapshot.writer;

import com.github.seijuro.publicdata.snapshot.SnapshotRequest;

public interface AbstractSnapshotWriter {
    public abstract int write(SnapshotRequest request);
}
