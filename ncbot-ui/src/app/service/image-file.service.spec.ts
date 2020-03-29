import { TestBed } from '@angular/core/testing';

import { ImageFileService } from './image-file.service';

describe('ImageFileService', () => {
  let service: ImageFileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageFileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
