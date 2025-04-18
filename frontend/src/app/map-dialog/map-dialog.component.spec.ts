import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapDialogComponent } from './map-dialog.component';

describe('MapDialogComponent', () => {
  let component: MapDialogComponent;
  let fixture: ComponentFixture<MapDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MapDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MapDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
