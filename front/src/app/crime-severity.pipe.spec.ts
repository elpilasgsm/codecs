import { CrimeSeverityPipe } from './crime-severity.pipe';

describe('CrimeSeverityPipe', () => {
  it('create an instance', () => {
    const pipe = new CrimeSeverityPipe();
    expect(pipe).toBeTruthy();
  });
});
