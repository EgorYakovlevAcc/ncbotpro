import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstAndLastMessagesEditorComponent } from './first-and-last-messages-editor.component';

describe('FirstAndLastMessagesEditorComponent', () => {
  let component: FirstAndLastMessagesEditorComponent;
  let fixture: ComponentFixture<FirstAndLastMessagesEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FirstAndLastMessagesEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FirstAndLastMessagesEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
