import { TestBed } from '@angular/core/testing';

import { SanctionsService } from './sanctions.service';

describe('SanctionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SanctionsService = TestBed.get(SanctionsService);
    expect(service).toBeTruthy();
  });
});
