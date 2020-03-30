import { TestBed } from '@angular/core/testing';

import { ScoreRangeResultService } from './score-range-result.service';

describe('ScoreRangeResultService', () => {
  let service: ScoreRangeResultService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScoreRangeResultService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
