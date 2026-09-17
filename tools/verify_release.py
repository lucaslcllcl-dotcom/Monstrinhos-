#!/usr/bin/env python3
from pathlib import Path
import re, sys
root=Path(__file__).resolve().parents[1]
text='\n'.join(p.read_text(errors='ignore') for p in root.rglob('*') if p.is_file() and p.suffix in {'.java','.gradle','.xml'})
gradle=(root/'app'/'build.gradle').read_text(errors='ignore')
vc=re.search(r'versionCode\s+(\d+)',gradle)
vn=re.search(r"versionName\s+['\"]([^'\"]+)['\"]",gradle)
version_ok=bool(vc and vn and int(vc.group(1))>=4100 and vn.group(1).startswith('4.1.'))
checks={'package':'com.caeless.monstrinhos' in text,'save':'monstrinhos_save_v1' in text,'version':version_ok,'launcher':'android.intent.category.LAUNCHER' in text,'no_admob':'com.google.android.gms.ads' not in text,'no_billing':'com.android.billingclient' not in text,'campaign_30':'safeStage' in text and 'Math.min(30' in text,'save_validation':'validateReleaseState' in text,'accessibility':'textScale()' in text and 'shouldAnimate()' in text}
for k,v in checks.items(): print(('OK ' if v else 'FAIL ')+k)
if vc and vn: print(f'VERSION code={vc.group(1)} name={vn.group(1)}')
sys.exit(0 if all(checks.values()) else 1)
