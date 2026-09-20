#!/usr/bin/env python3
from pathlib import Path
import re, sys
root=Path(__file__).resolve().parents[1]
text='\n'.join(p.read_text(errors='ignore') for p in root.rglob('*') if p.is_file() and p.suffix in {'.java','.gradle','.xml'})
gradle=(root/'app'/'build.gradle').read_text(errors='ignore')
vc=re.search(r'versionCode\s+(\d+)',gradle)
vn=re.search(r"versionName\s+['\"]([^'\"]+)['\"]",gradle)
# Accept the reconstructed Monstrinhos 4.x line from 4.1 onward. Do not pin
# verification to one minor version, otherwise a valid 4.2+ bump blocks CI.
version_ok=False
if vc and vn:
    parts=vn.group(1).split('-',1)[0].split('.')
    try:
        major, minor = int(parts[0]), int(parts[1])
        # RC1 moved the app to the 5.x release-candidate line. Keep accepting
        # reconstructed 4.1+ builds while allowing all 5.x+ release lines.
        version_ok = int(vc.group(1)) >= 4100 and ((major == 4 and minor >= 1) or major >= 5)
    except (ValueError, IndexError):
        version_ok=False
checks={'package':'com.caeless.monstrinhos' in text,'save':'monstrinhos_save_v1' in text,'version':version_ok,'launcher':'android.intent.category.LAUNCHER' in text,'no_admob':'com.google.android.gms.ads' not in text,'no_billing':'com.android.billingclient' not in text,'campaign_30':'safeStage' in text and 'Math.min(30' in text,'save_validation':'validateReleaseState' in text,'accessibility':'textScale()' in text and 'shouldAnimate()' in text}
for k,v in checks.items(): print(('OK ' if v else 'FAIL ')+k)
if vc and vn: print(f'VERSION code={vc.group(1)} name={vn.group(1)}')
sys.exit(0 if all(checks.values()) else 1)
