package com.relocation.relocation.Service;

import com.relocation.relocation.Model.Relocation;

import java.util.List;

public interface RelocationService {

    Relocation registerRelocation(Relocation relocation);

    Relocation updateRelocation(Relocation relocation);

    void deleteRelocation(Relocation relocation);

    List<Relocation> relocationList();

    Relocation findRelocationBySerialNumber(Relocation relocation);
}
